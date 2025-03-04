package com.patterns.behavioral.mediator;

/**
 * Mediator interface. It defines methods which will be needed by concrete mediator .
 *
 * Mediator should be aware of list of objects which will need mediator (hence will need addUSer() for this chat application)
 *
 * It will also contain a method which defines purpose of mediator. (Here chat mediator will be used to send message to all Users)
 */
public interface ChatMediator {

		void addUser(User user);

		void sendMessage(String msg, User sendBy);
}
