package com.patterns.behavioral.chainOfResponsibility;

import java.util.HashMap;
import java.util.Map;

public class PasswordValidator extends AuthHandler{

		private Map<String, String> passwordCache;

		public PasswordValidator() {
				this.passwordCache = new HashMap<>();
				this.passwordCache.put("Max", "Max@123");
				this.passwordCache.put("John", "John@987");
		}

		@Override public boolean handle(AuthRequest request) {
				String password = this.passwordCache.get(request.getUserName());
				if (password == null){
						System.out.println("Password not set for user  : "+request.getUserName());
						return false;
				}

				if (!password.equals(request.getPassword())){
						System.out.println("Invalid password");
						return false;
				}

				return nextHandler != null ? nextHandler.handle(request) : true;
		}
}
