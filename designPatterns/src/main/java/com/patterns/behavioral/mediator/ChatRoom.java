package com.patterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete mediator.
 */
public class ChatRoom implements ChatMediator{

		private List<User> users = new ArrayList<>();


		@Override public void addUser(User user) {
				user.addMediator(this);
				users.add(user);
		}

		@Override public void sendMessage(String msg, User sendBy) {
				for(User user : users) {
						if(user != sendBy){
								user.receiveMessage(msg);
						}
				}
		}
}
