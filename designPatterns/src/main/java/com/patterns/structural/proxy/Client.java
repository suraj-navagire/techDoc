package com.patterns.structural.proxy;

/**
 * Client interact with proxy object rather than original object.
 */
public class Client {
		public static void main(String[] args) {
				System.out.println("Proxy pattern started");

				IUserService userService = new UserServiceProxy();
				User user = userService.getUser("001");
				System.out.println("User : "+ user);

				User user1 = userService.getUser("001");
				System.out.println("User : "+ user1);


				System.out.println("Proxy pattern ended");
		}
}
