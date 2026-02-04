package com.systemdesign.lld.ratelimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiter {
		private Map<String, Bucket> keyTokenMap = new ConcurrentHashMap<>();
		private RateLimiterConfig config;

		public TokenBucketRateLimiter(RateLimiterConfig config) {
				this.config = config;
		}

		@Override public boolean isRequestAllowed(String key) {

				if(!keyTokenMap.containsKey(key)){
					keyTokenMap.put(key, new Bucket(config));
				}

				Bucket bucket = keyTokenMap.get(key);

				return bucket.consume();
		}
}
