package com.patterns.structural.proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * Proxy class. This class adding caching to store User so that every time we don't need to call UserService which actually calls to db.
 */
public class UserServiceProxy implements IUserService{

		private IUserService service = new UserService();

		private Map<String, User> userCache = new HashMap<>();

		@Override
		public User getUser(String userId) {
				User user = userCache.get(userId);
				if (user == null){
						user = service.getUser(userId);
						userCache.put(userId, user);
				}

				return user;
		}
}
