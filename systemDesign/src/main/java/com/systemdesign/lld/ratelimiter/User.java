package com.systemdesign.lld.ratelimiter;

public class User implements Runnable {
		private String userId;
		private RateLimiter rateLimiter;

		public User(String userId, RateLimiter rateLimiter) {
				this.userId = userId;
				this.rateLimiter = rateLimiter;
		}

		@Override public void run() {
				while (true){
						boolean isAllowed = rateLimiter.isRequestAllowed(userId);

						System.out.println("User id : "+ userId+" allowed "+isAllowed);
						try {
								Thread.sleep(1000);
						} catch (InterruptedException e) {
								throw new RuntimeException(e);
						}
				}
		}
}
