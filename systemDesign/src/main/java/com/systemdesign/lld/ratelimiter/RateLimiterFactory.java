package com.systemdesign.lld.ratelimiter;

public class RateLimiterFactory {

		public static RateLimiter getRateLimiter(RateLimiterType type , RateLimiterConfig config){
				RateLimiter rateLimiter = null;
				switch (type){
				case FIXED_WINDOW:
						throw new RuntimeException("Implementation is not present");
				case SLIDING_WINDOW:
						throw new RuntimeException("Implementation is not present");
				case TOKEN_BUCKET:
						rateLimiter = new TokenBucketRateLimiter(config);
						break;
				default:
				}

				return rateLimiter;
		}
}
