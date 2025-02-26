package com.patterns.structural.proxy;

/**
 * This service is used to return user. It fetches user from database.
 */
public class UserService implements IUserService{

		@Override
		public User getUser(String userId) {
				return loadFromDB(userId);
		}

		private User loadFromDB(String userId){
				System.out.println("Load user from db : "+userId);
				return new User("001", "Tom");
		}
}
