package com.systemdesign.lld.ratelimiter;

public class RateLimiterConfig {
		private final double capacity;
		private final double refillRate;

		public RateLimiterConfig(double capacity, double refillRate) {
				this.capacity = capacity;
				this.refillRate = refillRate;
		}

		public double getCapacity() {
				return capacity;
		}

		public double getRefillRate() {
				return refillRate;
		}
}
