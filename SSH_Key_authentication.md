# SSH Key Authentication 
## The Problem Without Keys

Normally, when you SSH into a server, it asks for a password.

Passwords can be guessed, intercepted, or mistyped.

If you run automated jobs (like Jenkins), storing passwords is unsafe.

So we use SSH key pairs instead.

## What’s in an SSH Key Pair

When you run:

```
ssh-keygen -t rsa -b 4096
```

You get two files:

Private key → id_rsa (keep this secret, never share it)

Public key → id_rsa.pub (safe to share, goes to the server)

## How Authentication Works

Think of this like a lock and key analogy:

The server holds your public key (like installing a lock on the door).

You keep the private key on your local machine (like keeping the key in your pocket).

When you try to SSH in:

The server sends a random challenge.

Your machine uses your private key to “sign” the challenge.

The server checks the signature against the public key it has on file.

If they match, you’re in — no password needed.

## One-Way Safety

Public key → can’t be used to guess the private key.

Private key → must be kept safe; anyone with it can log in as you.

If someone steals only your public key, it’s useless without the private key.

## Setting It Up

Generate keys (on Jenkins machine, for example):

```
ssh-keygen -t rsa -b 4096
```

Copy public key to remote server:
```
ssh-copy-id user@remote-server
```

This appends your public key to:
```
~/.ssh/authorized_keys
```

Test login:
```
ssh user@remote-server
```

If it logs in without asking for a password — success.

## Why Jenkins Loves This

Jenkins stores the private key in its Credentials store (secure vault).

Jenkins uses the private key to authenticate without exposing passwords.

The server only needs the public key, which is harmless by itself.