package com.patterns.behavioral.mediator;

/**
 * Object that needs mediator to communicate with different objects.
 *
 * It holds mediator reference.
 */
public abstract class User {

		protected String userName;

		protected ChatMediator mediator;

		public User(String userName) {
				this.userName = userName;
		}

		abstract void sendMessage(String msg);

		abstract void receiveMessage(String msg);

		abstract void addMediator(ChatMediator mediator);
}
