package com.systemdesign.lld.ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiter {
		private Map<String, Bucket> keyTokenMap = new ConcurrentHashMap<>();
		private RateLimiterConfig config;

		public TokenBucketRateLimiter(RateLimiterConfig config) {
				this.config = config;
		}

		@Override public boolean isRequestAllowed(String key) {
				//Synchronization is not needed for this method. As Internally ConcurrentHashMap is synchronized
				Bucket bucket = keyTokenMap.computeIfAbsent(key, v -> new Bucket(config));

				//Need lock inside bucket as same user or ip can send multiple request at a time.
				//In that case they will share same Bucket.
				return bucket.consume();
		}
}
