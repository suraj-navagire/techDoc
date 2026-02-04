package com.systemdesign.lld.ratelimiter;

public interface RateLimiter {
		boolean isRequestAllowed(String key);
}
