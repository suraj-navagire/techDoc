package com.systemdesign.lld.ratelimiter;

import java.util.concurrent.locks.ReentrantLock;

public class Bucket {
		private final double capacity;

		//If this contains 1. means 1 token per second. If 0.2 then 1 token in 5 second.
		private final double refillRate;
		private double availableTokens;
		private long lastRefillTimeStamp;

		private ReentrantLock lock;

		public Bucket(RateLimiterConfig config){
				this.capacity = config.getCapacity();
				this.refillRate = config.getRefillRate();
				this.availableTokens = capacity;
				this.lastRefillTimeStamp = System.currentTimeMillis();
		}

		public boolean consume(){
				boolean isConsumed = false;
				lock.lock();
				if(availableTokens > 0){
						availableTokens = availableTokens - 1;
						isConsumed = true;
				}
				lock.unlock();
				return isConsumed;
		}

		private void refill(){
				lock.lock();
				long now = System.currentTimeMillis();
				long timeDiffMillis = now - lastRefillTimeStamp;

				double timeDiffSeconds = timeDiffMillis / 1000.0;

				double tokensToAdd = timeDiffSeconds * refillRate;

				availableTokens = Math.min(capacity, availableTokens + tokensToAdd);
				lastRefillTimeStamp = System.currentTimeMillis();

				System.out.println("timeDiffSeconds : "+timeDiffSeconds+" , tokensToAdd : "+tokensToAdd+", capacity : "+capacity );
				lock.unlock();
		}
}
