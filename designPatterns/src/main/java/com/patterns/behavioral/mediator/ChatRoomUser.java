package com.patterns.behavioral.mediator;

/**
 * Concrete object that will need mediator to communicate with other objects.
 */
public class ChatRoomUser extends User{

		public ChatRoomUser(String userName) {
				super(userName);
		}

		@Override void sendMessage(String msg) {
				/**
				 * Telling his mediator to send message
				 */
				mediator.sendMessage(msg, this);
		}

		@Override void receiveMessage(String msg) {
				System.out.println("Message received by : "+userName+", Message : "+msg);
		}

		@Override void addMediator(ChatMediator mediator) {
				this.mediator = mediator;
		}
}
