YouTube System Design (Google Interview Ready)

1. Problem Statement

Design YouTube's large-scale video platform where creators upload long-form videos / Shorts and viewers watch them with low latency at massive scale.


---

2. Requirements

Functional

1. Creator can upload videos.


2. Viewer can watch videos.


3. Support multiple resolutions (240p/480p/720p/1080p).


4. Show video metadata (title, creator, tags).


5. Track views, likes, comments.


6. Search, recommendations, subscriptions, likes/comments.



Non-Functional

1. Low startup latency (<2 sec).


2. High availability (99.99%).


3. Massive scale.


4. Global delivery.


5. Eventual consistency acceptable for counters.




---

3. Capacity Estimation

Assumptions:

Daily Active Users (DAU): 250 Million

10% creators = 25 Million creators/day

Avg uploads/creator/day = 2 videos

Avg video size uploaded = 60 MB compressed

Avg views/user/day = 20 videos

Avg streamed data/video = 25 MB adaptive bitrate

Peak traffic factor = 3x average


Writes (Uploads)

Uploads/day = 25M × 2 = 50M videos/day

Write QPS = 50M / 86400 ≈ 579 uploads/sec

Peak write QPS ≈ 1.7K/sec


Read Traffic (Views)

Views/day = 250M × 20 = 5 Billion views/day

Read QPS = 5B / 86400 ≈ 57.8K req/sec

Peak read QPS ≈ 173K req/sec


Storage

Raw uploads/day = 50M × 60 MB = 3 EB/day (too high for real world, indicates assumption upper bound)

Realistically dedupe + lower avg size + creator subset active simultaneously lowers effective storage.

If average real stored/transcoded footprint = 150 MB/video:

Daily storage = 50M × 150 MB = 7.5 PB/day


Metadata

1 KB/video metadata n- 50M/day = 50 GB/day

10 years ≈ 182 TB metadata


CDN Bandwidth

5B views × 25 MB = 125B MB/day ≈ 125 PB/day egress



---

4. APIs

Upload Video

POST /v1/videos

{
  "title": "trip vlog",
  "description": "Goa beach",
  "tags": ["travel","goa"]
}

Response:

{
  "videoId": "v123",
  "uploadUrl": "pre-signed-url"
}

Watch Video

GET /v1/videos/{videoId}

Response:

{
  "videoId": "v123",
  "manifestUrl": "https://cdn/.../master.m3u8",
  "title": "trip vlog"
}

Home Feed / Recommendations

GET /v1/home?cursor=abc

Search Videos

GET /v1/search?q=music

Channel Videos

GET /v1/channels/{channelId}/videos


---

5. High Level Architecture

Client App
   |
Load Balancer
   |
API Gateway
   |
+-------------------------------+
| Upload Service               |
| Video Metadata Service       |
| Recommendation Service                 |
| Engagement Service           |
+-------------------------------+
   |
Kafka / PubSub Event Bus
   |
Video Processing Pipeline
(Transcode, Thumbnail, HLS/DASH)
   |
Object Storage (Video Chunks)
   |
CDN Edge Nodes
   |
Viewer Playback


---

6. Upload Flow

1. Client requests upload session.


2. Upload service returns pre-signed object storage URL.


3. Client uploads directly to object storage.


4. Metadata saved in DB with status = PROCESSING.


5. Event sent to Kafka with videoId.


6. Workers transcode to multiple resolutions.


7. Generate HLS/DASH chunks + manifest.


8. Push assets to CDN.


9. Update status = READY.



Why Direct Upload?

Avoids app servers becoming bottleneck for large files.


---

7. Watch Flow

1. User opens YouTube video.


2. Recommendation service returns suggested next videos.


3. Video service returns metadata + manifest URL.


4. Client fetches chunks from nearest CDN edge.


5. Player auto-switches quality based on bandwidth.




---

8. Database Design

Metadata DB (NoSQL)

Use Cassandra / DynamoDB / Bigtable.

Video Record:

{
  "videoId": "v123",
  "creatorId": "u10",
  "title": "trip vlog",
  "status": "READY",
  "manifestUrl": "...",
  "createdAt": 123456
}

Why NoSQL:

Huge scale

Key-value access by videoId

Horizontal partitioning


Relational DB

Use for payments/ads/admin workflows if needed.


---

9. Search / Recommendation System

Separate service computes ranking using:

Watch history n- Likes/comments

Similar users

Creator popularity

Freshness

Completion rate


Use offline ML + online re-ranking. Cache first page heavily.


---

10. Caching Strategy

Redis for hot metadata.

CDN for video chunks.

Home page cache for anonymous/new users.

Local client prefetch next 2 videos.



---

11. Scaling Strategies

Read Scaling

CDN absorbs 95%+ traffic.

Stateless video metadata services behind LB.

Read replicas for DB.


Write Scaling

Shard uploads by creatorId.

Kafka partitions by videoId.

Autoscale transcoding workers.



---

12. Reliability / Failure Handling

Retry failed transcoding jobs.

Idempotent upload requests.

Multi-region object storage replication.

Circuit breaker on downstream services.

Dead letter queue for poison messages.



---

13. Security

Signed upload URLs.

Signed CDN URLs for private media.

Content moderation pipeline.

Rate limiting at API gateway.



---

14. Bottlenecks & Tradeoffs

1. Transcoding is compute expensive.


2. Viral creators create traffic spikes.


3. Feed generation expensive in real time.


4. Counter updates use eventual consistency.


5. Multi-region replication increases cost.




---

15. Interview Deep-Dive Questions

1. How would you support YouTube Live streaming?


2. How would you delete copyrighted videos globally?


3. How to count views accurately?


4. How to serve low-end networks in India?


5. How to reduce CDN cost?




---

16. Final Rating

Original Draft: 5.5/10

Good instincts, rough numbers, missing architecture clarity.

Final Version: 9.2/10

Strong enough for L4/L5 interview discussion if explained confidently.


---

17. What Impresses Google Interviewers

Clear assumptions

Back-of-envelope math

Tradeoffs

Why CDN + object storage + async pipeline

How to scale recommendations/search separately from media delivery
