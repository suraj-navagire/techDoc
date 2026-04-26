# 📸 Instagram System Design — Google Interview Ready (Explained Per Service)

---

## 1. System Context

- DAU = **50M**
- System is **read-heavy**
- Feed is the **dominant component**

---

# 2. Follow Service

### API
POST /follow  
{ followerId, followeeId }

---

### Assumptions
- 1 follow per user per week  

---

### Throughput
- Daily follows:
50M / 7 ≈ 7.14M/day  

- QPS:
7.14M / 86400 ≈ ~82 req/sec  

👉 Insight:
- Very **low write traffic**
- Not a bottleneck

---

### Storage
- Record size ≈ 24B  
7.14M × 24B × 365 × 10 ≈ ~625 GB  

👉 Insight:
- Fits easily in distributed DB

---

### Bandwidth
7.14M × 24B / 86400 ≈ ~2 KB/sec  

👉 Insight:
- Negligible network impact

---

### Design Decision
- Use **NoSQL (Cassandra)**
- Store:
  - following list
  - followers list  

👉 Needed for fast feed fan-out

---

# 3. Post Service

### API
POST /post  
{ userId, mediaUrl, caption }

---

### Assumptions
- 10% users post daily → **5M/day**
- Avg metadata size ≈ **7KB**

---

### Throughput
5M / 86400 ≈ ~58 QPS  

👉 Insight:
- Moderate writes

---

### Storage
5M × 7KB × 365 × 10 ≈ ~127 TB  

👉 Insight:
- Metadata is large but manageable  
- Media stored separately (CDN/S3)

---

### Bandwidth
5M × 7KB / 86400 ≈ ~0.4 MB/sec  

👉 Insight:
- Not network heavy (because media is offloaded)

---

### Design Decision
- Metadata → NoSQL  
- Media → Object Storage + CDN  
- Publish event to Kafka  

---

# 4. Like Service

### API
POST /like  
{ userId, postId }

---

### Assumptions
- 10 likes/user/day  

---

### Throughput
50M × 10 = 500M/day  
≈ ~5,800 QPS  

👉 Insight:
- Very high write QPS

---

### Storage
500M × 24B × 365 × 10 ≈ ~43 TB  

---

### Bandwidth
500M × 24B / 86400 ≈ ~139 KB/sec  

👉 Insight:
- High QPS but small payload → manageable

---

### Design Decision
- NoSQL with composite key (postId, userId)
- Prevent duplicate likes (idempotency)

---

# 5. Comment Service

### API
POST /comment  
{ userId, postId, comment }

---

### Assumptions
- 5 comments/user/day  
- Avg size = 500B  

---

### Throughput
50M × 5 = 250M/day  
≈ ~2,900 QPS  

---

### Storage
250M × 500B × 365 × 10 ≈ ~456 TB  

👉 Insight:
- **Huge storage growth**

---

### Bandwidth
250M × 500B / 86400 ≈ ~1.45 MB/sec  

---

### Design Decision
- NoSQL
- Index by postId (for fetching comments)

---

# 🚨 6. Feed Service (MOST IMPORTANT)

### API
GET /feed?userId=&cursor=&limit=20

---

### Assumptions
- 100 feed opens/user/day  

---

### Throughput
50M × 100 = 5B/day  
≈ ~58,000 QPS  

👉 Insight:
- **This dominates entire system**

---

### Bandwidth (Naive)
5B × 7KB / 86400 ≈ ~405 MB/sec  

---

### Optimized Bandwidth
- Return lightweight data (~1KB)

≈ ~60 MB/sec  

👉 Insight:
- Still the **biggest bottleneck**

---

### Design Decision

#### Fan-out on Write
- Push posts to followers’ feed

#### Problem
- Celebrity users → millions of writes

---

### Final Strategy (Hybrid)
- Normal users → fan-out on write  
- Celebrities → fan-out on read  

---

### Feed Pipeline
Post → Kafka → Feed Fanout Service  
                  ↓  
            Fetch followers  
                  ↓  
         Store in Redis (Feed Cache)  

---

### Why Cache?
👉 Because:
- 58K QPS cannot hit DB directly  
- Redis gives **<10ms latency**

---

# 7. Sharding Strategy

- Follow → shard by userId  
- Feed → shard by userId  
- Post → shard by postId  

---

### Hot User Handling
- Split celebrity users across partitions  
- Use consistent hashing  

---

# 8. Ranking Layer

Feed is not chronological.

Score = f(recency, engagement, user affinity)

---

# 9. Failure Handling

- Kafka replication  
- Idempotent consumers  
- Cache fallback to DB  
- Eventual consistency accepted  

---

# 10. Final System Insight

- Feed = **core bottleneck (58K QPS, ~60 MB/sec)**  
- Likes/Comments = high QPS but small payload  
- Follow/Post = low impact  

---

## 🎯 Interview Closing Line

> “I designed the system around feed as the bottleneck, optimizing it using a hybrid fan-out strategy, caching, and sharding, while keeping write services scalable through NoSQL and event-driven architecture.”

---
