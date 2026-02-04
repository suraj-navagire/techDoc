package com.systemdesign.lld.ratelimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Client {

		//Consider this as applications rate limiter which will manage all client request
		private static RateLimiter rateLimiter;

		static {
				initializeRateLimiter();
		}

		public static void main(String[] args) throws InterruptedException {
				System.out.println("Rate Limiter application started");

				int users = 5;
				//Each thread will mimic individual client.
				ExecutorService executorService = Executors.newFixedThreadPool(users);

				for(int i=0;i<users; i++){
						User user = new User(String.valueOf(i), rateLimiter);
						executorService.submit(user);
				}

				executorService.awaitTermination(1, TimeUnit.HOURS);


				System.out.println("Rate Limiter application ended");
		}


		private static void initializeRateLimiter(){
				//This config tells each capacity is 1 and refill rate is 0.2 means each user can access application once in 5 second.
				RateLimiterConfig config = new RateLimiterConfig(1, 0.2);

				rateLimiter = RateLimiterFactory.getRateLimiter(RateLimiterType.TOKEN_BUCKET, config);
		}
}
