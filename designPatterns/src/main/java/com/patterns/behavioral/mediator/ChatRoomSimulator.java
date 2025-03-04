package com.patterns.behavioral.mediator;

public class ChatRoomSimulator {

		public static void main(String[] args) {
				System.out.println("ChatRoomSimulator started");

				/**
				 * Chat room is mediator between multiple users to communicate with each other.
				 */
				ChatMediator indianChatRoom = new ChatRoom();

				/**
				 * Creating new Chat User.
				 */
				User user1 = new ChatRoomUser("User1");
				User user2 = new ChatRoomUser("User2");
				User user3 = new ChatRoomUser("User3");


				/**
				 * Three users decided to join indian chat room.
				 */
				indianChatRoom.addUser(user1);
				indianChatRoom.addUser(user2);
				indianChatRoom.addUser(user3);

				/**
				 * User 1 sending message
				 */
				user1.sendMessage("My First msg to this room");

				System.out.println("ChatRoomSimulator ended");
		}
}
