package com.patterns.behavioral.chainOfResponsibility;

import java.util.HashSet;
import java.util.Set;

public class UserNameValidator extends AuthHandler {

		private Set<String> userNames = new HashSet<>();

		public UserNameValidator() {
				userNames.add("Max");
				userNames.add("John");
		}

		@Override public boolean handle(AuthRequest request) {

				if(!userNames.contains(request.getUserName())){
						System.out.println("Invalid username : "+ request.getUserName());
						return false;

				}
				return nextHandler != null ? nextHandler.handle(request) : true;
		}
}
