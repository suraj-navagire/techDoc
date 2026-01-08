package com.patterns.structural.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * This is a proxy class. Proxy class is used to control access.
 * Here proxy class is controlling access of real service invocation (UserService) and storing result in cache so that every time we don't need to call UserService
 * which actually calls to db. This proxy class is caching the User's. So this is caching proxy.
 */
public class UserServiceProxy implements IUserService{

		private IUserService service = new UserService();

		private Map<String, User> userCache = new HashMap<>();

		private static final Logger LOGGER = Logger.getLogger(UserServiceProxy.class.getName());

		@Override
		public User getUser(String userId) {
				//Here we are logging the user. which is possible because of proxy.
				LOGGER.fine("Reading user : "+userId);
				//Here we are caching user, which is possible because of proxy.
				User user = userCache.get(userId);
				if (user == null){
						user = service.getUser(userId);
						userCache.put(userId, user);
				}

				return user;
		}
}
