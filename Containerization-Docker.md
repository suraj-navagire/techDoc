# Containerization / Docker

## What is a container?

A container is an isolated environment for running an application.

A container does not automatically contain a complete operating system or its own kernel. The image creator decides what application code, runtime, and user-space files it contains. Containers share the host's Linux kernel.

## `scratch` container

`scratch` is essentially an empty base image.

If an application is statically compiled and needs no OS user-space libraries, it can run from `scratch`. Some Go or Rust applications are examples.

```text
Go/Rust application
        ↓
Host Linux kernel
        ↓
Hardware
```

Therefore, OS libraries are not mandatory for every container.

## Java container

A normal Java application needs a JVM/Java runtime to execute a JAR.

The JVM includes native, OS- and CPU-specific components. A Java runtime image therefore needs compatible user-space libraries for those components.

For example, an Alpine-based JRE image:

```text
Spring Boot JAR
        ↓
JVM / Java Runtime
        ↓
Alpine OS libraries
        ↓
Host Linux kernel
        ↓
Hardware
```

## Normal OS machine vs Docker container

```text
NORMAL OS MACHINE                             CONTAINER SETUP (DOCKER)

Installed applications                        Inside container image
┌──────────────────────────────┐              ┌──────────────────────────────┐
│ Java app / browser / database│              │ Java application             │
├──────────────────────────────┤              ├──────────────────────────────┤
│ JRE/JDK, Python, Node.js     │              │ JRE + app dependencies       │
├──────────────────────────────┤              ├──────────────────────────────┤
│ OS libraries                 │              │ Container OS libraries       │
│ libc / WinAPI                │              │ libc / certificates / shell  │
└──────────────────────────────┘              └──────────────────────────────┘
              │                                             │
              ▼                                             ▼
Comes with installed OS                       Shared host OS — not in container
┌──────────────────────────────┐              ┌──────────────────────────────┐
│ OS kernel                    │              │ Host OS kernel               │
│ Device drivers               │              │ Host OS device drivers       │
│ OS services/tools            │              │                              │
└──────────────────────────────┘              └──────────────────────────────┘
              │                                             │
              ▼                                             ▼
Comes with physical hardware                  Same physical hardware
┌──────────────────────────────┐              ┌──────────────────────────────┐
│ BIOS/UEFI firmware           │              │ BIOS/UEFI firmware           │
│ CPU, RAM, SSD, network card  │              │ CPU, RAM, SSD, network card  │
└──────────────────────────────┘              └──────────────────────────────┘
```

The container's OS libraries are inside the image; they make system calls to the host OS kernel.

## Why Alpine?

If we use:

```dockerfile
FROM eclipse-temurin:21-jre-alpine
```

the container provides:

```text
Java Runtime/JVM + Alpine user-space libraries (musl, certificates, etc.)
```

The JVM's native components use compatible libraries from the image. Alpine provides user space, not the Linux kernel.

## Container vs without a container

### Without a container

```text
Application → JVM → Host OS libraries → Host Linux kernel → Hardware
```

### With a container

```text
Application → JVM → Container OS libraries (for example, Alpine/musl)
            → Host Linux kernel → Hardware
```

The container uses its own user-space libraries but shares the host kernel.

## What must be compatible?

An Alpine container does not require an Alpine host. It can run on Ubuntu, Debian, RHEL, and other Linux distributions because they provide a Linux kernel.

Compatibility still matters:

- CPU architecture: `amd64` versus `arm64`.
- Linux kernel: required kernel features and a sufficient version.
- Native dependencies: they must match the container's user-space environment.

## Final rule

> **Container = application + required runtime/user space.**  
> **Host = Linux kernel + device drivers + hardware.**

The container distribution does not need to match the host distribution, but the runtime, native libraries, CPU architecture, and kernel capabilities must be compatible.

## How containers achieve isolation

Containers are isolated using Linux kernel features:

```text
Container A app ─┐
Container B app ─┼→ Shared host Linux kernel → Hardware
Container C app ─┘
```

- **Namespaces:** each container sees its own processes, network, filesystem, and hostname.
- **cgroups:** limit CPU, memory, disk I/O, and process usage.
- **Security rules:** restrict permissions and system calls.

So a container is an isolated group of processes, not a separate OS or kernel.

## Namespace example

A **namespace** is a naming boundary. It lets the same name or ID exist separately in different groups without conflict.

```text
Company A has employee ID 1
Company B has employee ID 1
```

Both are valid because each company has its own namespace.

In Linux containers, the kernel creates separate PID namespace objects:

```text
Container A
PID namespace ID = 101
PID 1 = application process

Container B
PID namespace ID = 102
PID 1 = application process
```

Both containers can have `PID 1`:

```text
(Container A / namespace 101 / PID 1) → Host PID 5000
(Container B / namespace 102 / PID 1) → Host PID 6000
```

The kernel records which namespace each process belongs to and returns the PID visible inside that namespace.

Why use namespaces?

- Avoid name/ID conflicts.
- Isolate one group from another.
- Give each group its own view of resources.
- Improve security and organization.

In Docker, namespaces make each container feel like it has its own processes, network, filesystems, and hostname, even though they share the same host kernel.

## Basic Docker commands

### Create and start a container

```bash
docker run hello-world
```

`docker run` creates a container from the image and starts it. By default, its output is attached to the current terminal. The `hello-world` container prints its message and exits.

```bash
docker ps       # List running containers
docker ps -a    # List all containers, including stopped containers
```

### Create without starting

```bash
docker create hello-world
```

Start an existing container and attach the current terminal to its output:

```bash
docker start -a <container_id>
```

`-a` means **attach**.

### Stop and remove containers

```bash
docker stop <container_id>
```

`docker stop` sends a graceful stop signal, then waits 10 seconds by default before forcefully stopping the container.

```bash
docker kill <container_id>
```

`docker kill` immediately stops the container.

```bash
docker container prune
```

This removes stopped containers. `docker system prune` is broader: it can also remove unused networks, images, and build cache.

### View logs

```bash
docker logs <container_id>
```

### Pass a command when running a container

```bash
docker run busybox echo hi there
```

The command after the image overrides the image's default `CMD`. In this example, `echo hi there` runs inside the BusyBox container.

### Execute a command in a running container

```bash
docker exec -it <container_id> <command>
```

- `-i` keeps standard input open.
- `-t` allocates a terminal for interactive formatting.

Start a shell in a running container:

```bash
docker exec -it <container_id> sh
```

Run a new container and start its shell:

```bash
docker run -it <image_name> sh
```

## Container-to-container networking

If a database container and Tomcat container run on the same Docker network, Tomcat should connect to the database using the database container name, not `localhost`.

```bash
docker network create my-network

docker run -d --name my-db --network my-network \
  -e POSTGRES_USER=myuser \
  -e POSTGRES_PASSWORD=mypassword \
  -e POSTGRES_DB=mydb \
  postgres:13

docker run -d --name my-tomcat --network my-network \
  -p 8080:8080 \
  my-tomcat-image
```

From `my-tomcat`, the database host can be `my-db`. Docker's user-defined network provides name resolution for that container name.

List Docker networks:

```bash
docker network ls
```

## Copy a Docker image to another server

On the source server, save the image to a tar file:

```bash
docker save -o /scratch/obdxdev/kafka/obdxkafka-3.4.0.tar obdxkafka-3.4.0
```

Copy it to the destination server:

```bash
scp /scratch/obdxdev/kafka/obdxkafka-3.4.0.tar \
  obdxdev@10.180.57.124:/scratch/obdxdev/kafka/
```

On the destination server, load and run the image:

```bash
docker load -i /scratch/obdxdev/kafka/obdxkafka-3.4.0.tar
docker run -it obdxkafka-3.4.0 /bin/bash
```

Use `docker start <container_id>` only to restart a container that was already created earlier.
