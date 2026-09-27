# DNS Resolution and Amazon Route 53

## Core DNS Terms

| Term | Meaning |
|---|---|
| Domain registrar | Company where you register a domain name, such as GoDaddy. |
| DNS resolver | The recursive DNS service that looks up DNS records for a user; often provided by an ISP, company network, or public DNS provider. |
| Root name server | Directs a resolver to the correct top-level domain (TLD) name servers. |
| TLD name server | Handles a top-level domain such as `.com` and identifies the domain's authoritative name servers. |
| Authoritative name server | Stores the DNS records for a domain and returns the final answer. |
| Hosted zone | Amazon Route 53 container for DNS records for a domain. |

## DNS Resolution When GoDaddy Manages DNS

### Domain setup

1. Register `example.com` with GoDaddy.
2. The `.com` registry stores the domain delegation to GoDaddy's authoritative name servers, for example:
   - `ns01.domaincontrol.com`
   - `ns02.domaincontrol.com`
3. GoDaddy's name servers store DNS records, for example:

   `www.example.com` -> `203.0.113.10`

> `203.0.113.10` is a documentation-only example IP address.

### Lookup flow

1. A user enters `https://www.example.com` in a browser.
2. The browser and device first check their DNS caches.
3. If no cached answer exists, the device asks its configured **recursive DNS resolver** for the IP address of `www.example.com`.
4. The resolver asks a **root name server** where to find `.com`.
5. The root name server directs the resolver to the `.com` TLD name servers.
6. The resolver asks a `.com` TLD name server which authoritative name servers handle `example.com`.
7. The TLD name server returns GoDaddy's authoritative name servers.
8. The resolver asks a GoDaddy authoritative name server for `www.example.com`.
9. GoDaddy returns the matching DNS record, such as an A record containing `203.0.113.10`.
10. The resolver caches the answer for the record's TTL and returns it to the browser.
11. The browser connects to the application endpoint.

```text
Browser -> Recursive resolver -> Root name server -> .com TLD name server
        -> GoDaddy authoritative name server -> DNS record -> Application endpoint
```

## Move DNS Management to Amazon Route 53

### Name server update

1. Keep the domain registered with GoDaddy.
2. Create a **public hosted zone** for `example.com` in Amazon Route 53.
3. Route 53 assigns four authoritative name servers, for example:
   - `ns-123.awsdns-45.com`
   - `ns-678.awsdns-90.net`
   - `ns-234.awsdns-56.org`
   - `ns-789.awsdns-01.co.uk`
4. In GoDaddy, replace the existing name servers with the four Route 53 name servers.
5. GoDaddy, as the registrar, submits the delegation update to the `.com` registry.
6. The `.com` registry now directs DNS resolvers to Route 53 rather than GoDaddy DNS.

### Lookup flow after the update

1. A user requests `www.example.com`.
2. The recursive resolver follows the same root and `.com` TLD lookup process.
3. The `.com` TLD name server returns the Route 53 authoritative name servers.
4. The resolver asks Route 53 for the DNS record.
5. Route 53 returns the matching record.
6. The browser connects to the returned application endpoint.

```text
Browser -> Recursive resolver -> Root name server -> .com TLD name server
        -> Route 53 authoritative name server -> DNS record -> Application endpoint
```

## Record Types to Remember

| Record type | Use |
|---|---|
| A | Maps a domain name to an IPv4 address. |
| AAAA | Maps a domain name to an IPv6 address. |
| CNAME | Maps one DNS name to another DNS name. |
| Route 53 Alias | AWS-specific record that points to supported AWS resources, such as an Elastic Load Balancer, Amazon CloudFront distribution, or Amazon S3 static website endpoint. |

## Cloud Practitioner Memory Rule

**Registrar manages domain registration.**  
**Route 53 hosted zone manages DNS records.**  
**The domain's name servers decide which DNS provider is authoritative.**
