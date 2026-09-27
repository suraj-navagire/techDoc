# AWS Cloud Practitioner Notes

> Full Markdown conversion of the original notes. Language has been kept simple; common spelling and AWS service-name errors have been corrected.

## How to Use These Notes

- **High priority** — appears directly in the CLF-C02 exam guide or is a frequently tested service-selection concept.
- **Useful background** — helps understanding but is lower priority for the exam.
- **Out of scope / low priority** — useful AWS knowledge, but do not spend much exam-preparation time on it.

Read the service summaries first, then use the detailed notes and the comparison tables to revise. When a service name is shortened, its full official name appears in the reference table below.

## Contents

1. [Prerequisites](#prerequisites)
2. [Cloud Concepts](#cloud-concepts)
3. [AWS Service Name Reference](#aws-service-name-reference)
4. [Compute, containers, and load balancing](#24-aws-compute-services)
5. [Networking and content delivery](#43-amazon-virtual-private-cloud-amazon-vpc)
6. [Storage](#56-aws-storage-services)
7. [Databases, analytics, and integration](#61-database-services)
8. [Infrastructure, monitoring, and governance](#76-infrastructure-and-application-deployment)
9. [AI, ML, and generative AI](#79-aws-aiml-layers)
10. [Security and compliance](#81-security)
11. [Migration, backup, and disaster recovery](#82-aws-migration-and-transfer)
12. [Account management, billing, and support](#85-aws-account-management)

## AWS Service Name Reference

Use the full name the first time a service appears. The shorter name can be used afterward.

| Short name in notes | Official AWS service name |
|---|---|
| EC2 | Amazon Elastic Compute Cloud (Amazon EC2) |
| EBS | Amazon Elastic Block Store (Amazon EBS) |
| EFS | Amazon Elastic File System (Amazon EFS) |
| ECS | Amazon Elastic Container Service (Amazon ECS) |
| EKS | Amazon Elastic Kubernetes Service (Amazon EKS) |
| ECR | Amazon Elastic Container Registry (Amazon ECR) |
| Fargate | AWS Fargate |
| Lambda | AWS Lambda |
| ELB | Elastic Load Balancing (ELB) |
| VPC | Amazon Virtual Private Cloud (Amazon VPC) |
| S3 | Amazon Simple Storage Service (Amazon S3) |
| RDS | Amazon Relational Database Service (Amazon RDS) |
| DMS | AWS Database Migration Service (AWS DMS) |
| IAM | AWS Identity and Access Management (IAM) |
| KMS | AWS Key Management Service (AWS KMS) |
| ACM | AWS Certificate Manager (ACM) |
| WAF | AWS WAF |
| RAM | AWS Resource Access Manager (AWS RAM) |
| MGN | AWS Application Migration Service (AWS MGN) |
| DRS | AWS Elastic Disaster Recovery (AWS DRS) |
| CUR | AWS Cost and Usage Report (CUR) |
| CAF | AWS Cloud Adoption Framework (AWS CAF) |
| WA Tool | AWS Well-Architected Tool |
| NACL | Network Access Control List (Network ACL) |
| SNS | Amazon Simple Notification Service (Amazon SNS) |
| SQS | Amazon Simple Queue Service (Amazon SQS) |

## Prerequisites
### 1. Create a New AWS Account as the Root User

Go to [aws.amazon.com](https://aws.amazon.com).

**Note:** Gmail addresses can use plus addressing, for example `baseemail+newid@gmail.com`. All messages still arrive in the base mailbox. Each new AWS account has its own account ID and root user.

### 2. Verify That the AWS Account Is Active

Sign in as the root user using the email address and password. Select a Region, such as **US East (N. Virginia)**, then search for and open **Amazon EC2**. If the AWS Management Console opens without account-activation errors, the account is active.

**Note:** The AWS Management Console is the web interface used to manage AWS services after you sign in.

### 3. Create an AWS Budget

Create a budget so that you receive a notification when actual or forecast AWS costs exceed the selected amount.
Go to  Login with Root User -> Billing and Cost Management -> Budget -> Create Budget 
Use Template (Simplified) -> Monthly Cost Budget -> Give Budget Name -> Amount -> Provide email on which notification will come
Note : This budget will be applied to entire account. Not only limited to Root user who is creating it.

### 4. Avoid Using the Root User for Daily Work

The root user has unrestricted access to the AWS account. Do not use it for daily work; instead, use an IAM user or IAM Identity Center user with only the permissions required.
Create IAM user. Login with Root User -> Search IAM(Identity and Access Management) -> Users -> Create User
Provide a user name -> give the user access to the AWS Management Console -> choose the IAM user type -> provide a password.

Setup permissions -> As of now select attach policy directly and select AdministratorAcess policy. It is same as Root user excepti billing and other account related stuff.
After completing the steps, AWS provides a sign-in URL for the IAM user. The URL includes the account ID. Alternatively, the user can sign in from the AWS sign-in page by entering the account ID manually.

**Note:** IAM users and the root user belong to the same AWS account and use the same account ID.

There are two common AWS user types:
1. An **IAM user**, created in and restricted to one AWS account.
2. An **IAM Identity Center user**, centrally managed and able to access multiple AWS accounts.

### 5. Create an SSH Key Pair for Amazon EC2

To connect securely to a Linux EC2 instance, use SSH with an EC2 key pair. Key pairs are Region-specific: a key pair created in the Mumbai Region cannot be selected for an instance in US East (N. Virginia). Include the Region in the key-pair name.
Create a key pair: sign in as an IAM user -> select the required Region -> open **Amazon EC2** -> choose **Key pairs** -> create a key pair.
Enter a key-pair name -> choose **RSA** -> choose **PEM** -> create the key pair -> download and securely store the private key on your local computer.

**Note:** Key pairs are associated with an AWS account and Region.

### 6. Domain Name Registration
AWS resources can have IP addresses, which are difficult to remember. A domain name provides a human-readable address. You can purchase a domain from a registrar, such as GoDaddy. The registrar provides name servers that direct DNS queries to where the application is hosted.

Check how DNS resolution takes place before going ahead : DNS-resolution.txt

Sign in as an IAM user -> search for **Amazon Route 53** -> choose **Hosted zones** -> **Create hosted zone** -> enter the purchased domain name -> select **Public hosted zone** -> create it.

**Note:** Route 53 provides multiple name servers for high availability. Update the domain registrar with these name servers so that Route 53 can answer DNS queries for the domain.

## Cloud Concepts
### 7. What Is Cloud Computing?
Cloud computing is the on-demand delivery of IT resources over the internet with pay-as-you-go pricing.

### 8. Characteristics of Cloud
1. **On-demand self-service** — users provision resources without human interaction from the provider.
2. **Broad network access** — resources are accessible over the internet from heterogeneous clients, such as mobile devices and workstations.
3. **Resource pooling and multi-tenancy** — provider infrastructure is shared securely among customers through logical isolation.
4. **Rapid elasticity** — resources can scale out or scale in automatically as demand changes.
5. **Measured service** — usage is monitored, measured, and billed transparently.

### 9. 6 Advantages of cloud computing
1. **Pay as you go** — pay only for the resources you use.
2. **Benefit from economies of scale** — AWS can pass on cost benefits from operating large global data centers.
3. **Stop guessing capacity** — scale resources as demand changes.
4. **Increase speed and agility** — provision IT resources quickly through self-service.
5. **Stop running data centers** — AWS operates the underlying facilities and hardware.
6. **Go global in minutes** — deploy workloads in AWS Regions around the world.

### 10. Cloud deployment models
1. Public cloud - Data centers and Cloud services are owned and managed by third party provider. Resources are delivered over Internet to users.
2. Private cloud - Data centers and Cloud services are owned and managed by single organization for their own usaze. It is not avaiable for public like public cloud.
3. Hybrid cloud - It is combination of both. Some servers are on premise and for other usage it uses public cloud.

### 11. Types of Cloud computing service models
So cloud computing services are in in 3 models
1. IaaS (Infrastructure as a service) - Cloud providers manages hardware. Customer is responsible for deploying and managing software required to install application and appliation itsetlf. E.g. EC2
2. PaaS (Platform as a service) - Cloud providers manages hardware as well as software part required to deploy application. Customer is responsible for deploying and managingapplication itself. E.g. Elastic Beanstalk
3. SaaS (Software as a service) - Cloud providers manages hardware, software required to install application and application itself. E.g. AWS AI services,Gmail

Check Cloud-computing-service-model.png digram

### 12. What is AWS region and why it is needed
AWS provides data centers across the globe, AWS called it regions.
Regions are required for:
1. Low latency for end users
2. Data residency and compliance
3. Disaster recovery
4. Pricing difference
5. Availability of AWS services

### 13. Availability Zones
An AWS Region contains multiple isolated **Availability Zones (AZs)**. Each AZ consists of one or more data centers and has independent power, cooling, and physical security.
1. AZs are physically separated to reduce the risk of a shared disaster.
2. Each AZ has redundant power and networking.
3. AZs in the same Region are connected through low-latency, high-bandwidth networking.

**Relationship:** one Region contains multiple AZs; one AZ contains one or more data centers. Deploying across multiple AZs improves high availability.

### 14. Other AWS Infrastructure Locations
- **AWS Local Zones** — deploy workloads closer to users in a specific city for low latency.
- **AWS Wavelength Zones** — deploy workloads in telecommunications networks for ultra-low-latency 5G applications.
- **AWS Outposts** — AWS-managed hardware on customer premises for local processing and data-residency needs.
- **Edge locations** — cache and deliver content close to end users, primarily through Amazon CloudFront.


### 15. Why IAM Is Required
Organizations have people in roles such as networking, DevOps, development, database administration, and data science. Each role needs different AWS access. IAM authenticates identities and authorizes access according to the principle of least privilege.

### 16. IAM User
Attach IAM policies to an IAM user to grant only the required permissions. Enable multi-factor authentication (MFA) for additional account security.

### 17. Why AWS Is Called Web Services
AWS services are available through HTTPS APIs, the AWS Management Console, AWS CLI, and AWS SDKs. IAM controls access to AWS API actions.

### 19. Types of IAM Policies
There are two main IAM policy types:
- **Identity-based policies** are attached to IAM users, groups, or roles and define what those identities can do.
- **Resource-based policies** are attached to resources, such as Amazon S3 buckets, and define who can access the resource.

An IAM policy commonly includes:
1. **Service** — the AWS service, such as Amazon EC2.
2. **Effect** — `Allow` or `Deny`.
3. **Action** — permitted or denied API actions, such as `ec2:StartInstances`.
4. **Resource** — the AWS resource affected, such as a specific EC2 instance ARN.
5. **Condition** — optional rules that must be true for the policy statement to apply.

### 20. IAM User Groups
Instead of attaching the same policies to each individual IAM user, attach policies to an IAM group. For example, create a **Developers** group, attach developer permissions, and add each developer IAM user to that group.

### 21. IAM Roles
Use IAM users for long-term identities within one account. Use an IAM role when an AWS service, application, or identity from another AWS account needs access. Roles provide temporary credentials and should be preferred over long-term access keys.

### 22. Access Keys
You can interact with AWS through the AWS Management Console, AWS CLI, and AWS SDKs. The console uses an interactive sign-in method; the CLI and SDKs can use access keys, although IAM roles are preferred whenever possible.

To create an access key: sign in -> open **IAM** -> choose **Users** -> select the user -> create an access key -> securely store the access key ID and secret access key.

**Note:** Access keys authenticate an AWS identity to AWS APIs. SSH keys authenticate a user to an operating system on a remote server. They are different credentials.

### 23. IAM Audit Tools
1. **IAM Credential Report** — lists IAM users and credential details, including password/MFA status and access-key age.
2. **IAM Access Analyzer** — identifies unintended external access to supported resources and helps validate access policies.
3. **IAM Access Advisor** — shows the AWS services an IAM identity has accessed, helping you apply least-privilege permissions.

### 24. AWS Compute Services
1. **Amazon Elastic Compute Cloud (Amazon EC2)** provides virtual servers in AWS.

**Location hierarchy:** AWS Region -> Availability Zone -> data center infrastructure -> EC2 instance.

### 25. Amazon EC2 Configurations
Select these configurations when launching an EC2 instance:
1. **Instance type** — processor, CPU, memory, storage, and networking capacity.
2. **Storage** — persistent **Amazon EBS** volumes or temporary **instance store** volumes.
3. **Amazon Machine Image (AMI)** — operating system and initial software, such as Ubuntu or Red Hat Enterprise Linux.
4. **Amazon VPC** — network, subnet, and IP addressing.
5. **Security group** — stateful virtual firewall rules.
6. **Key pair** — SSH login credential for supported Linux instances.

Optional EC2 settings include:
1. **User data** — a startup script that can install software or configure the instance.
2. **IAM role** — grants the instance temporary permissions to use other AWS services, such as Amazon S3.
3. **Purchase option** — On-Demand, Spot, Reserved Instances, or Savings Plans.
4. **Tenancy** — shared, dedicated instance, or dedicated host.
5. **Tags** — key-value labels such as `Name`, `Owner`, and `Environment` for identification and cost allocation.

After launch, connect to an instance by using its public IPv4 address or DNS name when it is publicly reachable. The login user name depends on the selected AMI; for example, it might be `ec2-user` or `ubuntu`.

### 26. Amazon EBS vs. EC2 Instance Store
1. **Amazon Elastic Block Store (Amazon EBS)** is persistent, network-attached block storage for EC2. Choose its size and type. Data generally remains after an EC2 instance is stopped, subject to the volume's delete-on-termination setting.
2. **EC2 instance store** is temporary storage physically attached to the EC2 host. Data can be lost when the instance is stopped, terminated, or moved to another host. Use it only for temporary data, caches, or buffers.

### 27. Amazon Machine Image (AMI)
1. An AMI is a template for launching an EC2 instance.
2. It includes an operating system and can include application software and configuration.
3. Start with a base AMI, install required software, and create a **custom AMI** for repeatable launches.
4. Instances launched from the custom AMI include the installed software and configuration.
5. AMIs are Region-specific. Copy a custom AMI to another Region before using it there.

### 28. Amazon VPC Security Groups
A **security group** is a stateful virtual firewall that controls inbound and outbound traffic for resources such as Amazon EC2 instances.
- **Stateful:** if inbound traffic is allowed, the response traffic is automatically allowed out.
- **Default behavior:** a new security group blocks inbound traffic and allows outbound traffic by default.
- **Resource level:** rules apply to associated resources within a specific Amazon VPC.
- Security groups can be reused and are Region-specific.

### 29. Amazon EC2 Key Pairs
Create an EC2 key pair in AWS and select it when launching a supported EC2 instance. AWS stores the public key on the instance; keep the private key securely on your local computer. You can reuse a key pair for multiple instances in the same Region.

### 30. Amazon EC2 User Data
When launching an EC2 instance, you can provide user data: a script or configuration that runs during instance initialization. It is commonly used to install packages or configure the application. By default, user-data scripts run only on the first launch; configure cloud-init differently if you need them to run again.

### 31. IAM Roles for Amazon EC2
Attach an IAM role to an EC2 instance when its application needs to access another AWS service. The role supplies temporary credentials automatically.
- Without usable credentials, the application cannot authenticate to AWS.
- With credentials but insufficient permissions, the AWS API request returns an **AccessDenied** error.

### 32. Amazon EC2 Purchasing Options
- **On-Demand Instances:** Pay for running capacity with no long-term commitment. Best for short-term, unpredictable, development, and test workloads.
- **Spot Instances:** Use unused EC2 capacity at a large discount. AWS can interrupt a Spot Instance with a two-minute interruption notice, so use it only for fault-tolerant and flexible workloads.
- **Savings Plans:** Commit to a consistent hourly spend for one or three years in exchange for lower prices. You pay for the commitment whether or not the covered resources are running.
- **Compute Savings Plans** apply across eligible compute usage, including EC2, AWS Fargate, and AWS Lambda.
- **EC2 Instance Savings Plans** provide a discount for a specific EC2 instance family in a chosen Region.

### 33. Amazon EC2 Tenancy Options
- **Shared tenancy:** the underlying host is shared with other AWS customers.
- **Dedicated Instance:** the instance runs on hardware dedicated to one customer, but AWS controls host placement.
- **Dedicated Host:** a physical server dedicated to one customer, with visibility and control of host-level placement; useful for specific licensing or compliance requirements.

### 34. AWS Resource Tags
Tags are key-value labels attached to AWS resources. Use them for ownership, environment, automation, and cost allocation.

Examples: `Name=WebServer`, `Owner=Alex`, and `Environment=Production`.

### 35. AWS Fargate
AWS Fargate is serverless compute for containers. You define the container image, CPU, memory, networking, and permissions; AWS manages the underlying servers. You do not manage or connect to EC2 hosts through SSH.

### 36. Amazon Elastic Container Service (Amazon ECS)
Amazon ECS is AWS's managed container orchestration service. It runs and manages Docker-compatible containers.
- An ECS cluster is a logical group where tasks run.
- A task definition is a blueprint for running an application. It defines one or more containers, including the image, CPU/memory, ports, environment variables, logs, IAM role, and networking.
- A task is one running instance of a task definition.
- An ECS service keeps the required number of task instances running.
- Tasks can run using:
- AWS Fargate — serverless container compute; AWS manages servers.
- Amazon EC2 — containers run on EC2 instances that you manage.

ECS cluster
→ ECS service (desired count: 3)
→ Task 1
→ Task 2
→ Task 3

Each task can contain one or more containers.

### 37. Amazon Elastic Kubernetes Service (Amazon EKS)
Amazon EKS is AWS's managed Kubernetes service. AWS manages the Kubernetes control plane.
It has a similar goal to ECS—running containerized applications—but uses standard Kubernetes concepts:
- Pod — smallest deployable Kubernetes unit; contains one or more containers.
- Deployment — manages the desired number of pod replicas.
- Node — compute environment where pods run.
- Pods can run on:
- EC2 nodes
- AWS Fargate for Kubernetes

EKS cluster
→ Deployment (replicas: 3)
→ Pod 1
→ Pod 2
→ Pod 3

Each pod can contain one or more containers.

**Note:** ECS and EKS provide orchestration control-plane capabilities. Amazon EC2 and AWS Fargate provide the compute capacity where containers run.

### 38. Amazon Elastic Container Registry (Amazon ECR)
Amazon ECR is a managed container image registry. Developers push container images to ECR, and Amazon ECS or Amazon EKS pulls those images when starting containers.

### 39. AWS Lambda
- AWS Lambda is a serverless compute service. Upload code and its dependencies, then AWS runs the code as a Lambda function in response to an event or request.
- One Lambda execution environment processes one request at a time. AWS scales by creating more concurrent environments, subject to concurrency limits.
- You pay for requests and execution duration, measured using allocated memory and execution time (GB-seconds).
- Example: 1,000 requests using 1 GB of memory for 100 milliseconds each consume 100 GB-seconds.
- A Lambda invocation can run for a maximum of 15 minutes.
- Lambda is a good choice for event-driven, stateless, short-running work.
- Lambda can be triggered by Amazon API Gateway, Amazon Kinesis, Amazon S3, Amazon DynamoDB, and other supported services.
- Configure an IAM execution role to allow the function to access other AWS services.

USer -> Web -> API Gateway -> Lambda

### 40. Elastic Load Balancing (ELB)
- Elastic Load Balancing automatically distributes incoming application traffic across healthy targets, such as Amazon EC2 instances, containers, and IP addresses, in one or more Availability Zones.
- **Single entry point:** a load balancer accepts traffic and routes it to healthy targets.
- **Health checks:** it stops routing traffic to an unhealthy target until that target recovers.
- **High availability:** it can distribute traffic across multiple Availability Zones.
- **Load balancer types:**
- **Application Load Balancer (ALB):** layer 7 HTTP/HTTPS traffic.
- **Network Load Balancer (NLB):** high-performance layer 4 TCP, UDP, and TLS traffic.
- **Gateway Load Balancer (GWLB):** deploy, scale, and manage fleets of third-party virtual network/security appliances.
- **Classic Load Balancer (CLB):** legacy load balancer; use ALB or NLB for new workloads.
- **Schemes:**
- **Internet-facing:** receives public internet traffic.
- **Internal:** receives traffic only from within a VPC or connected private network.

### 41. Target Group
- A Target Group contains backend targets used by an Elastic Load Balancer to route incoming client traffic.
- Targets can be EC2 instances, IP addresses, containers, or Lambda functions, depending on the load balancer type.
- It performs health checks and sends traffic only to healthy targets.
- A target group itself does not auto scale; an AWS Auto Scaling group can add or remove Amazon EC2 targets in a target group.

User -> Web -> ALB -> Target Group -> EC2
-> EC2

### 42. AWS Auto Scaling Group
- An Auto Scaling group (ASG) manages a fleet of Amazon EC2 instances. It maintains the desired capacity and can automatically scale in or out.
- An ASG can be attached to a Target Group. New EC2 instances are registered with the Target Group; removed instances are deregistered.
- Types of Scaling Methods
- **Maintain desired capacity:** keeps a specified number of instances running.
- Manual: You manually set the desired capacity. Change desired capacity from 3 to 5.
- Scheduled: Increases or decreases capacity based on a pre-set timeline.
- Dynamic: Adjusts capacity automatically based on real-time system metrics like CPU utilization. 
- Predictive: Uses machine learning to forecast future traffic patterns and scale out ahead of demand. 

Without ASG:
ALB → Target Group → Manually managed EC2 instances

With ASG:
ALB → Target Group → ASG-managed EC2 instances

ASG launches EC2
→ EC2 registers in Target Group
→ health check passes
→ ALB starts routing traffic

### 43. Amazon Virtual Private Cloud (Amazon VPC)
- An Amazon VPC is an isolated virtual network in an AWS Region.
- Define one or more IPv4 CIDR blocks, for example `10.10.0.0/16`. A `/16` block has 16 network bits and 16 host bits.
- A VPC contains one or more subnets.

### 44. Subnet
- A subnet exists in exactly one Availability Zone. You can create multiple subnets in the same AZ.
- Example: `10.10.1.0/24` is an IPv4 subnet with 24 network bits and 8 host bits.

### 45. Route table
- A route table contains routes that determine where subnet or gateway traffic is directed.
- A subnet is public when its route table has a route to an internet gateway; otherwise, it is private.
- A VPC has a main route table. You can associate a subnet with a different route table.

Note: Why we need private vpc and subnet?
- To achive better isolation and security. to create public and private subnets.

### 46. Amazon VPC Internet Gateway
- An internet gateway connects an Amazon VPC to the public internet. It is attached to the VPC.

### 47. Amazon VPC NAT Gateway
- A NAT gateway lets resources in a private subnet initiate outbound connections to the internet or AWS services. It does not allow unsolicited inbound connections from the internet.
- For internet access, deploy a public NAT gateway in a public subnet with a route to an internet gateway. Add a private-subnet route that sends outbound traffic to the NAT gateway.

### 48. Default VPC
- AWS provides a default VPC in supported Regions for new accounts.
- It includes a default subnet in each Availability Zone, an internet gateway, and default routing/security configuration.
- You can select the default VPC and subnet when launching an EC2 instance. Default subnets assign public IPv4 addresses by default.
- If deleted, a default VPC can be recreated through the AWS console or CLI.

### 49. Network Access Control List (Network ACL)
- A network ACL is a subnet-level, stateless firewall. A security group is applied to an associated resource, such as an EC2 instance.
- Network ACLs support both `Allow` and `Deny` rules; security groups support only allow rules.
- Network ACL rules are evaluated in numerical order, starting with the lowest rule number.
- Because network ACLs are stateless, define inbound and outbound rules separately. Security groups are stateful, so response traffic is automatically allowed.

### 50. Amazon VPC Connectivity
**On-premises to Amazon VPC**
1. **AWS Site-to-Site VPN:** connects an on-premises network to an Amazon VPC through an encrypted IPsec tunnel over the public internet. It is usually quick and cost-effective to set up.
2. **AWS Direct Connect:** provides a dedicated private connection from on premises to AWS. Use it for predictable performance, high bandwidth, and lower-latency private connectivity.

**VPC-to-VPC connectivity**
1. **VPC peering:** directly connects two VPCs with non-overlapping CIDR ranges. Peering is not transitive.
2. **AWS Transit Gateway:** centrally connects many VPCs and on-premises networks, avoiding a complex mesh of individual peering connections.

**Remote-user connectivity**
1. **AWS Client VPN:** allows users and remote devices to securely connect to resources in an Amazon VPC.

**Note:** To access supported AWS services privately from a VPC, use VPC endpoints where appropriate. This can avoid NAT gateway data-processing charges.

A **VPC endpoint** lets private resources access supported AWS services privately. Traffic stays on the AWS network and does not require an internet gateway, NAT device, or VPN.
- **Gateway endpoint:** used for Amazon S3 and Amazon DynamoDB; there is no hourly endpoint charge.
- **Interface endpoint:** uses AWS PrivateLink and supports many AWS, partner, and customer services; charges generally apply per hour and per GB processed.

**AWS PrivateLink** is the technology behind interface VPC endpoints. It provides private connectivity between Amazon VPCs and supported services without traversing the public internet.

A VPC endpoint is the connection object in your virtual network; AWS PrivateLink powers interface endpoints.


### 51. Amazon Route 53
- Amazon Route 53 is AWS's highly available DNS, domain registration, and health-check service.
- It create a public hosted zone for public DNS, and a private hosted zone for private DNS within a VPC.
- Route 53 supports following DNS records  
1. A - If you want to map dns name with IPv4 ip
2. AAAA - If you want to map dns name with IPv6 ip
3. CNAME, Alias - If you want to map dns name with another dns name
- Route 53 supports following routing policies
1. Simple - Returns ip
2. Failover - Does health checks on IP's and returns healthy IP
3. Weighted - Depending on incoming request weight such as request coming from premuim user send them to fast server etc. 
4. Geolocation - Depending on requests region send them to respecive region machine 
5. Latency-based - Depending on requests it returns machine ip which is nearest to user.

52 Edge Location
- An AWS edge location is a point of presence used to deliver content or route traffic closer to users, reducing latency.
- AWS edge locations are used by services such as Amazon CloudFront, AWS Global Accelerator, and Amazon S3 Transfer Acceleration.

53 CloudFront
- Amazon CloudFront is AWS's content delivery network (CDN) service.
- CloudFront uses edge locations to cache and deliver static and dynamic web content, including HTML, images, and video.
- CloudFront can terminate TLS at edge locations and encrypt traffic between users and the service.

Note : AWS supports SSL/TLS termination on Application Load Balancers (ALBs) and Network Load Balancers (NLBs). 
If Cloudfront is used then CloudFront acts as an SSL/TLS termination and encryption endpoint

54 Global Accelerator
- Amazon CloudFront is a CDN for caching and delivering web content. AWS Global Accelerator is a network-layer service that routes TCP and UDP traffic over the AWS global network using static IP addresses.
- It improves performance by routing traffic to optimal endpoint.
- It provides two static IP addresses that clients can whitelist and use to connect to a Global Accelerator endpoint.
- AWS Global Accelerator supports Application Load Balancers, Network Load Balancers, and EC2 instances.


55 S3 Transfer Acceleration
- It is an S3 bucket feature that uses edge locations and network optimization protocols to speed up data transfer to S3. It is useful for large file uploads to S3.

Note : Use Amazon CloudFront for caching web content, HTTP/HTTPS web applications and APIs.
AWS Global Accelerator for running non-HTTP protocols like gaming (UDP), IoT (MQTT), or VoIP.
and S3 Transfer Acceleration specifically for fast long-dinstance uploads/downloads to S3

### 56. AWS Storage Services
- Amazon Elastic Block Store (Amazon EBS)
- Amazon Simple Storage Service (Amazon S3)
- Amazon Elastic File System (Amazon EFS) and Amazon FSx

### 57. Amazon Elastic Block Store (Amazon EBS)
- Amazon EBS provides persistent, network-attached block storage for Amazon EC2 instances.
- An EBS volume is generally attached to one EC2 instance at a time.
- Multi-Attach is supported only for Provisioned IOPS SSD (`io1` and `io2`) volumes in supported configurations.
- EBS volume types include:
- General Purpose SSD: gp2, gp3
- Provisioned IOPS SSD: io1, io2
- Throughput Optimized HDD: st1
- Cold HDD: sc1
- An EBS volume belongs to one Availability Zone (AZ).
- EBS volumes can be backed up using EBS snapshots.
- EBS snapshots are incremental: after the first snapshot, only changed blocks are saved.
- To move data to another Availability Zone, create a snapshot and create a new volume from that snapshot in the target AZ.
- EBS volumes have a DeleteOnTermination attribute.
- By default, the root EBS volume is usually deleted when the EC2 instance is terminated, while additional non-root volumes are usually retained.
- For long-term data retention, create a snapshot before deleting the EBS volume.

### 58. Amazon Simple Storage Service (Amazon S3)
- Amazon S3 is object storage accessible over the web using HTTP/HTTPS APIs.
- Create a bucket, then store objects in it. Each object is identified by its bucket name and object key.
- An S3 bucket stores objects. An object can be up to 5 TB, and a bucket can store an unlimited number of objects.
- S3 provides storage classes for different access frequency, retrieval latency, availability, and cost requirements.
- Common S3 storage classes include:
1. **S3 Standard** — frequent access, low latency, and high availability.
2. **S3 Standard-IA** — infrequently accessed data that still needs millisecond retrieval.
3. **S3 One Zone-IA** — lower-cost infrequent-access data stored in one Availability Zone; not suitable for data that cannot be recreated.
4. **S3 Intelligent-Tiering** — automatically moves eligible objects between access tiers based on access patterns.
5. **S3 Glacier storage classes** — low-cost archive storage with different retrieval times.
- S3 Glacier Instant Retrieval - Latency in seconds
- S3 Glacier Flexible Retrieval - latency in min - hours
- S3 Glacier Deep Archive - Latency in hours. Cheapest.
- S3 security includes Block Public Access, IAM policies, bucket policies, and ACLs. ACLs are generally legacy and bucket/IAM policies are preferred.
- S3 supports server-side encryption: SSE-S3, SSE-KMS, and SSE-C. Client-side encryption is also possible.
- S3 Lifecycle rules transition objects between storage classes or expire them after a defined duration.
- S3 can host a static website containing HTML, CSS, JavaScript, images, and similar static files. It cannot run server-side code such as PHP or Python.
- S3 Versioning stores multiple versions of an object and helps recover from accidental deletion or overwrite.
- S3 Replication supports:
- SRR — Same-Region Replication
- CRR — Cross-Region Replication
- Versioning must be enabled on both source and destination buckets.
- IAM Access Analyzer can identify S3 buckets that are public or accessible by external AWS accounts.

### 59. Amazon Elastic File System (Amazon EFS)
- Amazon EFS is fully managed, elastic shared file storage. Multiple Linux-based compute resources can access the same file system using NFS.
- EFS storage grows and shrinks automatically; you do not provision a fixed disk size.

### 60. Amazon FSx
- Amazon FSx provides fully managed, high-performance file systems for specialized workloads.
- Use **Amazon FSx for Windows File Server** for Windows/SMB file shares.
- Use **Amazon FSx for Lustre** for high-performance computing workloads and integration with Amazon S3.

**Note:** Amazon EFS uses NFS and is designed primarily for Linux/Unix workloads. Amazon FSx provides specialized file systems, including SMB for Windows, Lustre for HPC, and ONTAP/OpenZFS options.

| Use case | Recommended service |
|---|---|
| Hosting OS, boot volumes, and databases on EC2 | **Amazon EBS** |
| Lowest-latency block storage for EC2 | **Amazon EBS** |
| Applications on EC2 needing a shared filesystem | **Amazon EFS** |
| Persistent shared filesystem for containers | **Amazon EFS** |
| Distributed computing workloads | **Amazon EFS** or **Amazon FSx** |
| Media workflow applications | **Amazon EFS**, **Amazon FSx**, or **Amazon S3** |
| Social-media images and videos | **Amazon S3** |
| Hosting a static website | **Amazon S3** |
| Backup and archive | **Amazon S3** |
| Storing IoT data at scale | **Amazon S3** |
| Storing ML training data | **Amazon S3** |
| Data lake | **Amazon S3** |
| Exchanging data between organizations | **Amazon S3** |

### 61. AWS Database Services
| Database type | Use cases | AWS services |
|---|---|---|
| **Relational database** | Banking, finance, bookings, ERP, CRM | Amazon RDS, Amazon Aurora |
| **Key-value / NoSQL database** | Shopping carts, product catalogs, customer attributes | Amazon DynamoDB |
| **Document database** | Content management, personalization, mobile applications | Amazon DocumentDB |
| **In-memory database** | Leaderboards, real-time analytics, caching | Amazon ElastiCache, Amazon MemoryDB |
| **Graph database** | Fraud detection, social networking, recommendation engines | Amazon Neptune |
| **Time-series database** | IoT applications, event tracking, time-based metrics | Amazon Timestream |
| **Data warehouse** | Analytics, reporting, data marts | Amazon Redshift |
| **Ledger database** | Immutable systems of record, supply chain, health-care, and financial audit trails | **Amazon QLDB** *(historical note: do not choose this discontinued service for new workloads)* |
| **Vector database / vector search** | Generative AI, semantic similarity search, RAG, image/audio/document search | Amazon OpenSearch Service, Aurora PostgreSQL with `pgvector`, RDS for PostgreSQL with `pgvector`, Amazon DocumentDB, Amazon MemoryDB, Amazon Neptune Analytics, DynamoDB 

### 62. Amazon Relational Database Service (Amazon RDS)
- Amazon RDS is a managed relational database service. It supports engines such as PostgreSQL, MySQL, MariaDB, Oracle, and Microsoft SQL Server.
- **Read Replicas** are read-only copies that use asynchronous replication. Use them to scale read traffic and, where supported, promote a replica for disaster recovery. They are not the same as Multi-AZ standby instances.
- **Multi-AZ deployments** use synchronous replication to a standby instance in another Availability Zone for high availability. The standby is not used for normal read traffic in the standard Multi-AZ DB instance deployment.
- Amazon RDS supports automated backups, point-in-time recovery (PITR), and manual snapshots.
- Automated-backup retention can be configured from 0 to 35 days for DB instances. Setting it to 0 disables automated backups.
- PITR restores the database to a chosen time within the retention period, up to the latest restorable time. The latest restorable time is typically a few minutes behind the current time. Restoring from PITR creates a new DB instance.
- Manual snapshots are retained until explicitly deleted. They can be copied across Regions and, subject to engine/encryption restrictions, shared across AWS accounts.
- RDS backups and snapshots are stored in Amazon S3 internally, but you do not access that S3 location directly.
- RDS provides monitoring through Amazon CloudWatch and the RDS console, including metrics such as database connections, CPU utilization, free storage space, and disk I/O.
- RDS has a preferred weekly maintenance window, normally 30 minutes. You can choose the window; AWS applies pending maintenance during it when possible.
- You do not get operating-system access to the EC2 instances hosting a standard RDS database.
Exception: RDS Custom provides more access and responsibility for the underlying environment.

### 63. Amazon Aurora
- Amazon Aurora is a managed relational database compatible with MySQL and PostgreSQL. It is part of the Amazon RDS family but has a separate cloud-native architecture.
- Aurora storage automatically replicates six copies of data across three Availability Zones.
- Use Aurora Serverless when you want Aurora capacity to scale automatically with demand. It is not the same service as AWS Lambda.


### 64. SQL vs. NoSQL
**Choose SQL when:**
- You need ad-hoc queries, joins, or reporting.
- Your schema is mostly fixed.
- You need strong transactional consistency.
- Data has clear relationships.

**Choose NoSQL when:**
- Most queries are predefined and based on known access patterns.
- You need flexible data structures.
- You need very high throughput or easy horizontal scaling.
- Your data does not fit well into relational tables.

### 65. Amazon DynamoDB
- Amazon DynamoDB is a fully managed, serverless NoSQL key-value and document database.
- It provides single-digit millisecond read/write latency at large scale.
- DynamoDB supports two capacity modes:
- On-demand: automatically scales; no capacity planning.
- Provisioned: configure read/write capacity; suitable for predictable workloads.
- Data is encrypted at rest by default, and DynamoDB provides high availability across multiple Availability Zones.
- A table stores items—similar to rows in a relational database.
- An item contains attributes—similar to columns/key-value fields.
- A table has one of these primary-key designs:
Simple primary key:
Partition Key

Composite primary key:
Partition Key + Sort Key
- The partition key determines how DynamoDB distributes data.
- The sort key groups and orders related items that have the same partition-key value.
- A sort key is optional; it is not required for every table/item
- A **Query** retrieves items using a partition key; a **Scan** examines every item in a table or index.
- **Amazon DynamoDB Accelerator (DAX)** is an in-memory cache for DynamoDB.
- **Global Tables** provide multi-Region, multi-active replication, allowing writes in more than one Region.
- **DynamoDB Streams** capture item-level changes for event-driven processing.

### 66. Amazon DocumentDB
- Amazon DocumentDB (with MongoDB compatibility) is a fully managed document database.
- Used to store, query, and index JSON-like documents.
- DocumentDB storage automatically grows in increments of 10GB, up to 64 TB.
- It is designed to scale for high-throughput document workloads.
Use cases:
- User profile
- Content management like blogs, video metadata
- E-commerce product catalog (similar to DynamoDB)

### 67. Amazon ElastiCache
- Amazon ElastiCache is a fully managed in-memory data store/cache service. It provides high performance and very low latency for reads and writes.
- It can be used as a distributed cache.
- It reduces database load for read-intensive workloads by storing frequently accessed data in memory.
- Common caching strategies:
- Lazy loading / cache-aside: load data into the cache only after a cache miss.
- Write-through: update the cache whenever data is written to the database.
- TTL: expire cache data after a defined time.
- ElastiCache supports:
- Valkey
- Redis OSS
- Memcached
- ElastiCache can use serverless caching or node-based clusters. Serverless caching automatically handles capacity scaling and provides high availability across multiple Availability Zones. AWS ElastiCache deployment options
- Valkey and Redis OSS support advanced data structures, replication, snapshots, transactions, pub/sub, Multi-AZ, and cross-Region Global Datastores.
- Memcached is simpler and is suitable for basic distributed caching.
- Common uses include session storage and real-time caching.
Application
↓
ElastiCache
↓ Cache miss
Amazon RDS / database

### 68. Amazon Neptune
- Amazon Neptune is a graph database that stores data as a network of entities and relationships.
- A graph contains:
- Nodes (vertices): objects such as a person, game, book, or movie.
- Edges: relationships between nodes, such as FRIEND_OF, LIKES, PLAYS, or AUTHORED.
Person A ── FRIEND_OF ──> Person B
Person A ── PLAYS ──────> Football
Person B ── READS ──────> Book
- Amazon Neptune is a fully managed graph database service for highly connected data.
- Neptune Database is the operational graph database. It is designed for scalability and availability, including Multi-AZ deployments, read replicas, replication, and backups.
- Neptune Database is suitable for querying billions of relationships with millisecond latency.
- Neptune Analytics is the graph-analytics engine. It loads graph data into memory and runs built-in graph algorithms on very large datasets.
- Neptune Analytics can analyze graphs with tens of billions of relationships within seconds using graph algorithms.
- Neptune Analytics supports vector similarity search with graph traversals, making it suitable for GraphRAG and other Generative AI applications.
Use cases:
- Social networking
- Fraud detection
- Recommendation engines
- Route optimization
- Knowledge graphs, such as Wikipedia-like connected data
- Network security
- Drug discovery
- Generative AI and knowledge-graph use cases

### 69. Amazon Timestream
- Amazon Timestream is AWS's managed time-series database offering.
- Timestream for InfluxDB is compatible with InfluxDB workloads.
- Use it for:
- Live analytics
- Real-time IoT data ingestion and analytics
- Web/clickstream traffic
- Application and operational metrics
- DevOps monitoring
Note: Amazon Timestream for LiveAnalytics stopped accepting new customers on June 20, 2025. AWS recommends Timestream for InfluxDB for new workloads. AWS availability update

Amazon QLDB (quantum ledger database)
- Amazon QLDB was a quantum ledger database.
- It provided an immutable, cryptographically verifiable journal of data changes.
- Typical use cases included financial records, supply-chain systems, claim history, and trace-and-track systems.
Important: Amazon QLDB reached end of support on July 31, 2025, so it should not be selected for new workloads

### 70. AWS Database Migration Service (AWS DMS)
- AWS DMS is a managed service that migrates supported databases to and from AWS with minimal downtime. The source database can remain operational during migration.
- Use **AWS Schema Conversion Tool (AWS SCT)** when moving between different database engines and schema conversion is required.

### 71. Database vs. Data Warehouse vs. Data Lake
- **Database** -> operational application data.
- **Data warehouse** -> transformed data for reporting and analytics.
- **Data lake** -> low-cost storage for raw data at large scale.

### 72. AWS Data Streaming and Analytics Services
- Amazon Kinesis and Amazon MSK (Amazon Managed Streaming for Apache Kafka) capture real-time streaming data.
- Streaming data can be stored in operational databases such as Amazon RDS, Amazon DynamoDB, or Amazon Aurora.
- Data from databases can be extracted and transformed using ETL services such as AWS Glue or Amazon EMR.
- The transformed data can be loaded into Amazon Redshift, a data warehouse used for reporting and analytics.
- Amazon QuickSight can create business-intelligence dashboards and visualizations from Redshift data.

Kinesis / MSK
↓
RDS / DynamoDB / Aurora (Data store)
↓
AWS Glue / EMR
↓
Amazon Redshift (Data Store)
↓
Amazon QuickSight
- Amazon S3 is commonly used as a data lake to store large amounts of raw data.
- Data in S3 can be transformed using AWS Glue or Amazon EMR, then loaded into Redshift.
- Amazon Athena can query data directly in Amazon S3 without loading it into a database or data warehouse.

S3 Data Lake
↓
AWS Glue / EMR
↓
Redshift (Data Store)
↓
QuickSight (Dashboard)

Amazon Athena can be used to Direct query on S3 data


### 73. AWS Streaming and Analytics Services

| Service | Important information | Why choose it |
|---|---|---|
| **Amazon Kinesis Data Streams** | Captures and processes real-time streams such as logs, clickstreams, IoT events, and payment events. | Choose when you need AWS-native real-time streaming without Kafka. |
| **Amazon Kinesis Video Streams** | Captures and stores streaming video from cameras, drones, mobile devices, and other devices. | Choose it for CCTV, live video, video analytics, and IoT camera data. |
| **Amazon Managed Service for Apache Flink** | Fully managed Apache Flink service for real-time processing using Java, Python, Scala, or SQL. | Choose it to transform, filter, aggregate, and analyze streaming data continuously. |
| **Amazon MSK** | Fully managed Apache Kafka service. Producers publish events to Kafka topics; consumers read them. | Choose when your team uses Kafka tools, Kafka APIs, or needs Kafka compatibility. |
| **AWS Glue** | Serverless ETL service that discovers, transforms, and catalogs data. | Choose for simple serverless ETL jobs and maintaining metadata through the Glue Data Catalog. |
| **Amazon EMR** | Managed big-data platform for Apache Spark, Hadoop, Hive, Flink, and related frameworks. | Choose for large-scale/custom data processing where you need framework control. |
| **Amazon Redshift** | Managed cloud data warehouse for SQL analytics over large structured datasets. | Choose for BI reporting and fast analytical queries over transformed data. |
| **Amazon Athena** | Serverless SQL query service that queries data directly in S3. | Choose for ad-hoc analysis of S3 data without loading it into a database or warehouse. |
| **Amazon QuickSight** | AWS business-intelligence service for dashboards, reports, and visualizations. | Choose to create dashboards from Redshift, Athena, RDS, S3-based data, and other sources. |
| **Amazon Data Firehose** | Automatically delivers streaming data to destinations such as Amazon S3, Redshift, OpenSearch, Splunk, and HTTP endpoints. It can buffer, transform, and compress records. | Choose it when you want a simple managed way to deliver streaming data without building custom consumers. |

**Exam distinction:** Use **Amazon Kinesis Data Streams** when multiple consumers need to process the same streaming records. Use **Amazon Data Firehose** when you want managed delivery of streaming data to a destination such as Amazon S3, Amazon Redshift, or Amazon OpenSearch Service.

Producer
↓
Kinesis Data Stream
├── Consumer 1: Doing Fraud detection
├── Consumer 2: Building Real-time dashboard
└── Consumer 3: Calling Firehose to store data into S3 (Kenesis cannot directly store it can take help of Firehost to store data.)


### 74. Analytics Service Selection
| Use case | AWS service |
|---|---|
| Big-data processing | **Amazon EMR** |
| Streaming data analytics | **Amazon Managed Service for Apache Flink** |
| Transform and store streaming data | **Amazon Data Firehose** |
| ETL | **AWS Glue** or **Amazon EMR** |
| Ad-hoc SQL queries on S3 data | **Amazon Athena** |
| Business intelligence, dashboards, and graphs | **Amazon QuickSight** |
| Ingest sensor data using open-source Kafka | **Amazon MSK** |
| Big-data analytics / data warehouse | **Amazon Redshift** |
| Data discovery and data catalog | **AWS Glue** |
| Migration of Hadoop workloads | **Amazon EMR** |
| Ingest streaming data from mobile applications | **Amazon Kinesis Data Streams** |

### 75. AWS Application Integration Services

| Service | Where it sits / real role | Real example | Choose it when |
|---|---|---|---|
| **Amazon API Gateway** | API Gateway exposes HTTP endpoints and routes requests to a backend. The business logic is usually in Lambda, ECS, EC2, or another HTTP service. | `GET /users/101` → API Gateway → Lambda → DynamoDB | You need REST, HTTP, or WebSocket APIs for web/mobile clients. |
| **AWS AppSync** | AppSync exposes a GraphQL API. It retrieves/combines data from sources such as DynamoDB, Lambda, Aurora, OpenSearch, or HTTP APIs. | Mobile app requests `user { name orders { id } }` → AppSync → DynamoDB + Lambda | You need GraphQL, data aggregation from multiple sources, or real-time client updates. |
| **Amazon SQS** | A producer puts a message in a queue; a consumer pulls and processes it later. Messages remain until processed or expire. | Order service → SQS → payment-processing Lambda | You need reliable asynchronous processing, buffering, retries, and independent scaling. |
| **Amazon SNS** | A publisher sends one message to a topic; SNS pushes it to multiple subscribers. | `OrderCreated` → SNS → email service + SQS queue + Lambda | You need fanout notifications or broadcasting one event to many consumers. |
| **Amazon EventBridge** | An event bus receives events and uses rules to route matching events to targets. | `EC2 instance stopped` → EventBridge rule → Lambda / SNS / SQS | You need event-driven integration between AWS services, custom applications, or SaaS systems. |
| **Amazon MQ** | A managed traditional message broker for ActiveMQ Classic and RabbitMQ. Applications connect using standard broker protocols. | Existing Java JMS application → Amazon MQ ActiveMQ → existing consumer | You are migrating an existing ActiveMQ/RabbitMQ/JMS/AMQP application with minimal code changes. |


Amazon API Gateway also supports:
├── Authentication
├── Authorization
├── Throttling / rate limits
├── Request validation
├── CORS
└── Logging / monitoring
└── Caching


### 76. Infrastructure and Application Deployment
| Category | Service | What it does | Why choose it |
|---|---|---|---|
| Infrastructure as Code | **AWS CloudFormation** | Creates and manages AWS resources from declarative YAML or JSON templates. | Choose it to provision repeatable infrastructure such as VPCs, EC2, RDS, S3, and IAM roles. |
| Infrastructure as Code | **AWS CDK** | Lets you define AWS infrastructure using TypeScript, Java, Python, C#, or Go. CDK generates CloudFormation templates. | Choose it when developers want to create infrastructure using familiar programming languages. |
| Source control | **AWS CodeCommit** | Managed private Git repository service. | Choose it when you need AWS-hosted Git repositories; GitHub/GitLab can also be used as pipeline sources. |
| Build / test | **AWS CodeBuild** | Compiles source code, runs tests, and produces deployable artifacts. | Choose it when you do not want to manage build servers. |
| Deployment | **AWS CodeDeploy** | Automates deployments to EC2, ECS, Lambda, or on-premises servers. Supports rolling, blue/green, and canary deployments. | Choose it for controlled, repeatable deployments and safer releases. |
| CI/CD orchestration | **AWS CodePipeline** | Connects source, build, test, approval, and deployment stages into one automated workflow. | Choose it to automate the complete release process after every code change. |
| Platform as a Service | **AWS Elastic Beanstalk** | Deploys and manages web applications while provisioning infrastructure such as EC2, Auto Scaling Groups, Load Balancers, and CloudWatch. | Choose it when you want to deploy an application quickly without managing underlying infrastructure in detail. |
| Simplified cloud hosting | **Amazon Lightsail** | Provides simplified virtual servers, containers, managed databases, load balancers, storage, DNS, static IPs, and backups with predictable monthly pricing. | Choose it for simple websites, WordPress, small web applications, developer environments, and small container workloads. |


### 77. Infrastructure Management and Compliance
| Service | What it does | Why choose it |
|---|---|---|
| **AWS Systems Manager** | Centrally manages EC2 instances and on-premises servers. It supports running commands, patching, automation, inventory, and storing configuration values. | Choose it to operate many servers from one place instead of logging in to each server manually. |
| **AWS Session Manager** | A capability within Systems Manager that provides secure terminal access to managed instances through the AWS Console or CLI. | Choose it to access servers without opening SSH/RDP ports, public IPs, bastion hosts, or managing SSH keys. Sessions can be audited. |
| **AWS Config** | Records AWS resource configuration and configuration changes—for example, changes to security groups, S3 buckets, EC2, or IAM-related resources. It evaluates resources against compliance rules. | Choose it to track “who changed what,” view configuration history, and identify non-compliant resources. |

**Exam distinction:** AWS Config records resource configuration and evaluates compliance. AWS CloudTrail records API activity, such as which identity made a change.


### 78. AWS Monitoring, Logging, and Auditing
| Service | What it does | Why choose it |
|---|---|---|
| **Amazon CloudWatch** | Monitors AWS resources and applications using metrics, logs, alarms, dashboards, and events. Example: EC2 CPU usage, application logs, Lambda errors. | Choose it for operational monitoring and alerts when something is slow, unavailable, or failing. |
| **AWS CloudTrail** | Records API activity in an AWS account: who performed an action, what action, when, and from where. Example: who deleted an S3 bucket or changed a security group. | Choose it for auditing, security investigation, and compliance. |
| **AWS X-Ray** | Traces a request as it travels through application components such as API Gateway, Lambda, EC2, and databases. | Choose it to diagnose slow or failing requests in microservices/distributed applications. |
| **AWS Health Dashboard** | Shows AWS service events, planned maintenance, and issues that may affect your AWS resources. | Choose it to know whether a problem is caused by AWS infrastructure rather than your application. |

### 79. AWS AI/ML Layers
| Layer | Meaning | AWS AI/ML examples | Who does what? |
|---|---|---|---|
| **IaaS** | Infrastructure as a Service | Amazon EC2 GPU instances, AWS Trainium, AWS Inferentia | AWS provides servers/infrastructure; **you** install frameworks and build, train, and deploy models. |
| **PaaS** | Platform as a Service | Amazon SageMaker AI | AWS manages ML infrastructure and tools; **you** build, train, tune, and deploy your custom model. |
| **SaaS** | Software as a Service | Rekognition, Polly, Transcribe, Textract, Translate, Comprehend, Lex, Kendra, Forecast | AWS provides ready-made AI capabilities; **you** send data to an API and use the result. No ML model building needed. |


### 80. Prebuilt AWS AI/ML Services
| AWS service | What it does | When to use it / exam clue |
|---|---|---|
| **Amazon Rekognition** | Analyzes images and videos: detects objects, faces, text, labels, and unsafe content. | “Identify faces/products/text in photos or videos.” |
| **Amazon Polly** | Converts **text to natural-sounding speech**. | Build an app that reads articles, messages, or instructions aloud. |
| **Amazon Transcribe** | Converts **speech/audio to text**. | Create meeting notes, call transcripts, subtitles, or voice-to-text. |
| **Amazon Textract** | Extracts text, handwriting, tables, and form data from scanned documents. | Process invoices, receipts, forms, IDs, or PDFs. Think: **smart OCR**. |
| **Amazon Translate** | Translates text between languages. | Make an application or website multilingual. |
| **Amazon Comprehend** | Understands text: finds sentiment, key phrases, entities, language, and topics. | Analyze customer reviews, social media feedback, or support tickets. |
| **Amazon Kendra** | AI-powered enterprise search. Users ask questions in normal language and search internal documents. | Search company PDFs, FAQs, policies, SharePoint-like knowledge sources. |
| **Amazon Lex** | Builds chatbots and voice bots. | Customer-service chatbot, booking bot, or voice assistant. Uses the same core technology as Alexa. |
| **Amazon Forecast** | Predicts future values from historical time-series data. | Forecast product demand, inventory needs, sales, or staffing levels. |
| **Amazon Connect** | Cloud-based contact center / call center service. | Set up a customer support call center without buying contact-center infrastructure. Can integrate with Lex for chat/voice bots. |
| **Amazon SageMaker AI** | Managed platform to build, train, tune, and deploy your **own custom ML models**. | Use when prebuilt AI services are not enough and you need a custom model. |

### 81. Security
| AWS service | What it does | When to use it / exam clue |
|---|---|---|
| **AWS KMS** | Creates and manages encryption keys. | Encrypt data in S3, EBS, RDS, etc. Think: **key management for encryption**. |
| **AWS Certificate Manager (ACM)** | Provisions, manages, and renews SSL/TLS certificates. | Enable HTTPS for a website or load balancer. Think: **certificates**. |
| **AWS Secrets Manager** | Securely stores and can automatically rotate secrets such as database passwords and API keys. | Application needs database credentials without hardcoding them in code. Think: **secret/password rotation**. |
| **Amazon Macie** | Finds and protects sensitive data, especially in Amazon S3. | Detect PII such as names, credit-card numbers, or passport data in S3. |
| **AWS WAF** | Web Application Firewall; filters harmful HTTP/S web requests. | Protect a web app from SQL injection, cross-site scripting (XSS), bots, or unwanted IPs. |
| **AWS Shield** | Managed protection against DDoS attacks. | Protect public applications from DDoS. **Shield Standard** is automatic; **Shield Advanced** adds stronger protection/support. |
| **Amazon Inspector** | Automatically scans workloads for software vulnerabilities and unintended network exposure. | Find CVEs in EC2, container images in ECR, or Lambda dependencies. Think: **vulnerability scanner**. |
| **Amazon GuardDuty** | Continuously detects suspicious or malicious activity using logs, threat intelligence, and ML. | Alert on compromised accounts, unusual API behavior, malicious IPs, or suspicious S3 activity. Think: **threat detection**. |
| **AWS Security Hub** | Central dashboard that collects, organizes, prioritizes, and helps respond to security findings. | Need one view for findings from GuardDuty, Inspector, Macie, and security checks. Think: **central security dashboard**. |
| **Amazon Detective** | Helps investigate the root cause of a security finding using connected log and activity data. | GuardDuty raises an alert and you need to investigate “what happened?” Think: **security investigation**. |
| **AWS Artifact** | Self-service portal for AWS compliance reports and agreements. | Download AWS SOC, ISO, PCI reports, or review/sign agreements such as a BAA. Think: **AWS compliance documents**. |

### 82. AWS Migration and Transfer
| Service / area | What it does | Use it when the question says… |
|---|---|---|
| **AWS Application Migration Service (MGN)** | Migrates physical, virtual, or cloud servers to AWS with minimal downtime. This is a **lift-and-shift (rehost)** service. | “Move on-premises servers/VMs to Amazon EC2 with minimal changes.” |
| **AWS Database Migration Service (DMS)** | Migrates databases to AWS while keeping the source database running, reducing downtime. | “Migrate Oracle/MySQL/SQL Server database with minimal downtime.” |
| **DMS Schema Conversion** | Converts database schema/code when changing database engines. | “Move from Oracle to Aurora PostgreSQL/MySQL.” Use it with **DMS** for different database engines. |
| **AWS DataSync** | Automated, fast **online** transfer of files/data between on-premises storage and AWS storage such as S3 or EFS. | “Copy NFS/file-server data to S3/EFS,” “recurring data transfer,” or “automate file migration.” |
| **AWS Transfer Family** | Fully managed file transfer into/out of Amazon S3 or Amazon EFS using SFTP, FTPS, FTP, or AS2. | Useful background: existing partners/customers use SFTP/FTP. |
| **AWS Snow Family** | AWS sends a physical device so you can transfer very large data volumes offline. | “Petabytes/exabytes,” “poor/no network,” or “internet upload would take too long.” |
| **Amazon S3 Transfer Acceleration** | Speeds up internet uploads to an S3 bucket using AWS edge locations. | “Users globally upload files to S3 and need faster transfers.” |
| **AWS Storage Gateway** | Connects on-premises applications to AWS storage; enables **hybrid cloud storage**. | “Keep local applications but use S3/cloud storage,” “local cache with cloud-backed storage.” |
| **AWS Direct Connect** | Dedicated private network connection from on-premises to AWS. | “Consistent, private, high-bandwidth connection to AWS,” rather than normal internet/VPN. |
| **AWS Migration Hub** | Central location to track migration progress across AWS migration tools. | “Track multiple application/server migrations from one dashboard.” |
| **AWS Application Discovery Service** | Collects information about on-premises servers and dependencies for migration planning. | “Discover inventory, utilization, and dependencies before migration.” |

### 83. Disaster Recovery (DR) and Backup

**Backup** = a copy of data kept for recovery.

**Disaster recovery (DR)** = the plan and systems used to restore an application after a major failure.

Two key terms:
| Term | Meaning |
|---|---|
| **RTO** (Recovery Time Objective) | Maximum acceptable time to restore the application. |
| **RPO** (Recovery Point Objective) | Maximum acceptable amount of data loss, measured in time. |

Example: RPO = 1 hour means losing up to one hour of data is acceptable.

#### Four AWS DR strategies

| Strategy | Simple meaning | Cost | Recovery speed | Exam clue |
|---|---|---:|---:|---|
| **Backup and restore** | Keep backups; create infrastructure and restore data only after disaster. | Lowest | Slowest: hours | Low-priority workload; lowest cost acceptable. |
| **Pilot light** | Replicate critical data/core components to a second Region; start/scale application servers during disaster. | Low–medium | Tens of minutes | Core components ready, but application is mostly off. |
| **Warm standby** | A smaller, working version of production is always running in another Region. Scale it up during disaster. | High | Minutes | Reduced-capacity environment already running. |
| **Multi-site active/active** | Full application runs in multiple Regions and serves live traffic from each. | Highest | Near zero | Mission-critical workload; minimal downtime/data loss. |

#### AWS Elastic Disaster Recovery (AWS DRS)
| Service | What it does | When to use |
|---|---|---|
| **AWS Elastic Disaster Recovery** | Continuously replicates servers from on-premises or cloud environments to AWS. During a disaster, it launches recovery servers in AWS. | Recover physical/virtual/cloud servers with low RPO (seconds) and RTO (minutes), without continuously running a full second production environment. |

Think of DRS as an automated, cost-efficient pilot-light DR solution for servers.

#### AWS Backup

| Service | What it does | When to use |
|---|---|---|
| **AWS Backup** | Central service to define, schedule, automate, monitor, and restore backups across AWS services. | You need consistent backup policies for resources such as EBS, EC2, RDS, DynamoDB, EFS, and more. |

### 84. AWS Shared Responsibility Model

Security is shared between AWS and the customer.

| AWS is responsible for | Customer is responsible for |
|---|---|
| **Security of the cloud** | **Security in the cloud** |
| Physical data centers | Your data |
| Physical servers and hardware | Data classification and encryption choices |
| AWS global network | IAM users, roles, passwords, MFA, permissions |
| Host operating system and virtualization layer | Security group and network configuration |
| Physical security of AWS facilities | Application code and application security |
| | Patching the guest OS on EC2 instances |

#### AWS Acceptable Use Policy (AUP)

The AUP is the AWS policy that says what customers must not use AWS for.

| Prohibited use | Simple meaning |
|---|---|
| Illegal or fraudulent activity | Do not use AWS to break laws or commit fraud. |
| Security violations | Do not hack, attack, disrupt, or gain unauthorized access to systems. |
| Network abuse | Do not run DDoS attacks, malware, botnets, or harmful scans. |
| Spam | Do not send unsolicited bulk email/messages. |
| Harmful/offensive content or activity | Do not use AWS to harm others, promote serious violence, or exploit children. |
| Violating others’ rights | Do not infringe copyright, privacy, or other legal rights. |

### 85. AWS Account Management

| Service / concept | What it does | When to use it / exam clue |
|---|---|---|
| **AWS Organizations** | Centrally manages multiple AWS accounts. Create accounts, group them, apply governance policies, and manage billing. | Company has development, test, production, and security accounts. |
| **Management account** | The main account that creates and manages the organization. It pays the consolidated bill. | Central finance/admin account for all member accounts. |
| **Member account** | An AWS account that belongs to an AWS Organization. | Separate account for a team, application, or environment. |
| **Organizational Unit (OU)** | A logical group of AWS accounts inside Organizations. | Group all production accounts or all development accounts together. |
| **Service Control Policy (SCP)** | A central **permission boundary** for accounts/OUs. It limits the maximum permissions available. | Prevent member accounts from using a service or Region—for example, deny creating resources outside `ap-south-1`. |
| **Consolidated billing** | Combines charges from all member accounts into one bill, paid by the management account. | One payment method, centralized cost tracking, and potential volume discounts. |
| **AWS Control Tower** | Sets up and governs a secure multi-account AWS environment using AWS best practices. | Need a ready-made multi-account “landing zone” with governance controls/guardrails. |
| **AWS Resource Access Manager (RAM)** | Shares supported AWS resources across AWS accounts or within an organization. | Share a VPC subnet, Transit Gateway, Route 53 Resolver rule, or license instead of creating duplicates. |

**Exam distinction:** An SCP sets the maximum permissions available in an account or OU; it does **not** grant permissions. IAM or resource policies must still allow the action.

### 86. AWS Billing and Cost Management

Think in three stages:

| Need | Main tool |
|---|---|
| Estimate cost **before** using AWS | **AWS Pricing Calculator** |
| View and analyze actual spending | **Billing Dashboard / Cost Explorer** |
| Set limits and receive alerts | **AWS Budgets** |

| Tool | What it does | Exam clue / when to use |
|---|---|---|
| **AWS Billing and Cost Management Dashboard** | Main billing page showing current charges, bills, payment information, Free Tier status, trends, budgets, anomalies, and savings opportunities. | “View monthly bill,” “check current charges,” or “billing overview.” |
| **AWS Pricing Calculator** | Estimates the cost of planned AWS resources before deployment. | “Estimate cost before migrating/building a workload.” |
| **AWS Cost Explorer** | Visualizes and analyzes historical cost and usage; filters by service, Region, account, tag, etc.; forecasts future costs. | “Find why costs increased,” “analyze spending,” or “forecast future AWS cost.” |
| **AWS Budgets** | Set cost, usage, Savings Plans, or Reserved Instance budgets; sends alerts when actual or forecasted spending crosses a threshold. | “Alert me before I exceed ₹X/$X per month.” |
| **AWS Cost and Usage Report (CUR)** | Most detailed cost and usage data, with line items. Delivered for detailed reporting/analysis. | “Need detailed hourly or daily usage and cost data for reporting.” |
| **AWS Cost Anomaly Detection** | Detects unusual or unexpected cost increases and alerts you. | “Notify me when spending suddenly spikes.” |
| **Cost Allocation Tags** | Tags such as `Project`, `Department`, or `Environment` that let you split and track costs. | “Show costs separately for Dev, Test, Prod, or departments.” |
| **Cost Categories** | Groups cost using rules, such as mapping multiple accounts/tags to “Marketing” or “Production.” | “Organize costs into business-friendly categories.” |
| **AWS Trusted Advisor** | Provides recommendations, including cost optimization checks. | “Identify underused resources or cost-saving recommendations.” |
| **AWS Compute Optimizer** | Recommends better-sized EC2, EBS, Lambda, ECS, and related resources using utilization data. | “Right-size an overprovisioned EC2 instance.” |

### 87. AWS Well-Architected Framework and Cloud Adoption Framework
These are cloud design best practices from the AWS Well-Architected Framework.

| Principle | Simple meaning |
|---|---|
| **Stop guessing capacity** | Use Auto Scaling and elastic resources; do not buy fixed capacity in advance. |
| **Test at production scale** | Create a large test environment when needed, test, then shut it down. |
| **Automate experiments** | Use Infrastructure as Code (such as CloudFormation) so environments can be created, changed, and rolled back safely. |
| **Use evolutionary architectures** | Design systems so they can change and improve over time instead of being fixed forever. |
| **Make decisions using data** | Use metrics, logs, monitoring, and testing results to improve architecture. |
| **Improve through game days** | Regularly simulate failures/disasters to test systems and team response. |


#### AWS Well-Architected Framework
The AWS Well-Architected Framework is AWS guidance for designing and operating good cloud workloads.
It has six pillars:

| Pillar | Focus |
|---|---|
| **Operational Excellence** | Run, monitor, automate, and improve operations. |
| **Security** | Protect data, systems, and access. |
| **Reliability** | Recover from failures and meet demand. |
| **Performance Efficiency** | Use the right resources efficiently. |
| **Cost Optimization** | Avoid unnecessary spending. |
| **Sustainability** | Reduce environmental impact and use resources efficiently. |

#### AWS Well-Architected Tool

| Tool | What it does | Exam clue |
|---|---|---|
| **AWS Well-Architected Tool** | Lets you review a workload by answering questions aligned to the six pillars. It identifies risks and recommends improvements. | “Assess architecture against AWS best practices” or “identify high-risk issues.” |

WAF = framework/guidance.
WA Tool = AWS tool used to perform the review.


#### AWS Cloud Adoption Framework (AWS CAF)
The AWS Cloud Adoption Framework (CAF) helps an organization plan its overall cloud transformation—not just a single application architecture.

| CAF perspective | Simple focus |
|---|---|
| **Business** | Ensure cloud adoption supports business outcomes. |
| **People** | Skills, culture, teams, and organizational change. |
| **Governance** | Cost, risk, compliance, and portfolio management. |
| **Platform** | Build the cloud environment, networking, and infrastructure. |
| **Security** | Secure cloud workloads and meet compliance needs. |
| **Operations** | Run, monitor, support, and continuously improve services. |


### 88. AWS Support, Trusted Advisor, and Service Quotas

| Item | What it does | Cloud Practitioner exam clue |
|---|---|---|
| **AWS Support** | AWS help for account, billing, technical, and operational issues. | “Need AWS technical help” or “open a support case.” |
| **AWS Support Center** | Console area to open and manage support cases. | “Contact AWS Support.” |
| **AWS Trusted Advisor** | Gives recommendations to optimize cost, performance, security, fault tolerance, quotas, and operations. | “Find idle resources,” “improve security,” or “AWS best-practice recommendations.” |
| **AWS Service Quotas** | View, manage, and request increases to AWS service limits. | “Increase number of EC2 instances/VPCs/EIPs allowed.” |

#### AWS Support Plans
AWS support plans are evolving. For Cloud Practitioner questions, recognize the traditional plan names as well as the current names.

| Plan | Simple purpose | Key exam point |
|---|---|---|
| **Basic Support** | Included free for every AWS customer. | Account/billing help, documentation, AWS Health, and basic Trusted Advisor checks. **No technical support cases.** |
| **Developer Support** *(legacy, being retired)* | Technical support for development/testing. | Business-hours support; not for production-critical workloads. |
| **Business Support / Business Support+** | Recommended minimum support for production workloads. | 24/7 technical support, faster response, full Trusted Advisor checks. |
| **Enterprise Support** | Expert support for business-critical workloads. | Includes a **Technical Account Manager (TAM)** and very fast response for critical issues. |
| **Unified Operations** | Highest current tier for mission-critical environments. | Deepest operational support and expertise. |

#### AWS Trusted Advisor
Trusted Advisor checks your AWS environment and suggests improvements.

| Trusted Advisor category | Example |
|---|---|
| **Cost optimization** | Find idle EC2 instances or unused EBS volumes. |
| **Performance** | Identify configuration that may reduce performance. |
| **Security** | Detect exposed security groups or weak security configuration. |
| **Fault tolerance** | Identify workloads lacking redundancy. |
| **Service quotas** | Warn that an EC2/VPC/service limit is near its maximum. |
| **Operational excellence** | Recommend operational best practices. |

### 89. AWS Engagement Model
The AWS engagement model means the different ways a customer can get help, skills, and expertise while using AWS.

| Option | What it does | When to use it / exam clue |
|---|---|---|
| **AWS Professional Services** | AWS’s own expert consulting team helps customers plan, design, migrate, modernize, and implement AWS solutions. | Large or complex migration/transformation; need direct AWS expert guidance. |
| **AWS Partner Network (APN)** | Global network of AWS partner companies that provide consulting, software, managed services, training, and industry solutions. | Need a third-party AWS expert, managed service provider, or specialized industry solution. |
| **AWS Training and Certification** | Official AWS learning resources and role-based certifications. | Build AWS skills; validate knowledge with credentials such as Cloud Practitioner. |
| **AWS Skill Builder** | AWS learning platform with digital courses, learning plans, labs, and exam preparation. | Learn AWS services or prepare for a certification exam. |
| **AWS re:Post** | AWS-managed online community and knowledge hub with questions, answers, and official Knowledge Center articles. | Need community help, troubleshooting guidance, or common AWS answers. |
| **AWS Support** | Support plans for account, billing, and technical assistance directly from AWS. | Need an official support case or urgent technical help. |

### 90. AWS Generative AI *(Useful Background)*
| Service | What it does | When to use it |
|---|---|---|
| **Amazon Bedrock** | Fully managed service to build generative-AI applications using ready-to-use foundation models from Amazon and other providers. | Build a chatbot, content generator, document summarizer, image generator, or GenAI app without managing ML infrastructure. |
| **Amazon SageMaker AI** | Platform to build, train, fine-tune, and deploy custom ML/GenAI models. | Need more control or need to build/customize your own model. |

#### Important Generative AI Terms
| Term | Simple meaning |
|---|---|
| **Foundation Model (FM)** | Large pre-trained model that can perform many tasks, such as writing, summarizing, translating, and generating images. |
| **Large Language Model (LLM)** | A foundation model specialized in understanding and generating human language. |
| **Prompt** | The instruction/input you give the model. Example: “Summarize this report in three bullets.” |
| **Prompt engineering** | Writing better prompts to obtain better answers from the model. |
| **Inference** | The model generates an output from your input prompt. |
| **Hallucination** | Model gives an answer that sounds believable but is incorrect or unsupported. |
| **Fine-tuning** | Further customize a model using your own example data for a specific task. |
| **RAG** | Retrieval-Augmented Generation: retrieve relevant company documents/data first, then give that context to the model for a more accurate answer. |

**Exam note:** Amazon Bedrock is valuable AWS knowledge, but prioritize the AI/ML services explicitly listed in the CLF-C02 exam guide, such as Amazon Rekognition, Amazon Lex, Amazon Polly, Amazon Textract, Amazon Transcribe, Amazon Translate, Amazon Comprehend, and Amazon SageMaker AI.
