Docker, JVM, Alpine & Linux Kernel

1. What is a Container?

A container is an isolated environment for running an application.

A container does not automatically contain an OS or kernel.
The image creator decides what it contains.

Container
├── Application
├── Runtime
├── Libraries
└── Dependencies

---

2. "scratch" Container

"scratch" is essentially an empty base image.

If an application is statically compiled and needs no OS userspace libraries, it can run in "scratch".

Example: some Go/Rust applications.

Go/Rust application
        ↓
Linux system calls
        ↓
Host Linux Kernel
        ↓
Hardware

So, OS libraries are not mandatory for every container.

---

3. Java Container

A normal Java application needs a JVM/Java runtime to execute the JAR.

The JVM contains native, OS/CPU-specific components and therefore needs compatible OS libraries.

Example:

Spring Boot JAR
      ↓
JVM / Java Runtime
      ↓
OS libraries
      ↓
Linux system calls
      ↓
Host Linux Kernel
      ↓
Hardware

---

4. Why Alpine?

If we use:

FROM eclipse-temurin:21-jre-alpine

the container provides:

Java Runtime/JVM
        +
Alpine userspace libraries
(musl, etc.)

The JVM's native components use these compatible libraries.

Alpine provides userspace, not the Linux kernel.

---

5. Container vs Without Container

Without Container

Application
   ↓
JVM
   ↓
Host OS libraries
   ↓
Host Linux Kernel
   ↓
Hardware

With Container

Application
   ↓
JVM
   ↓
Container OS libraries
(e.g. Alpine/musl)
   ↓
Host Linux Kernel
   ↓
Hardware

The container uses its own userspace libraries, but shares the host kernel.

---

6. What Must Be Compatible?

An Alpine container does not require an Alpine host.

It can run on Ubuntu, Debian, RHEL, etc., because they provide a Linux kernel.

However, compatibility still matters:

- CPU architecture: "amd64" vs "arm64"
- Linux kernel: required features/version
- Native dependencies: must match the container's environment

Final Rule

«Container = application + required userspace/runtime.
Host = Linux kernel + hardware.»

The container distribution does not need to match the host distribution, but the runtime, native libraries, CPU architecture, and kernel capabilities must be compatible.
