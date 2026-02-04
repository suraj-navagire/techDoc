package com.systemdesign.lld.ratelimiter;

public class RateLimiterConfig {
		private final int capacity;
		private final double refillRate;

		public RateLimiterConfig(int capacity, double refillRate) {
				this.capacity = capacity;
				this.refillRate = refillRate;
		}

		public int getCapacity() {
				return capacity;
		}

		public double getRefillRate() {
				return refillRate;
		}
}
