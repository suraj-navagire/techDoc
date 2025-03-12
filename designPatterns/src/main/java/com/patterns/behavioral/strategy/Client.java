package com.patterns.behavioral.strategy;

public class Client {
		public static void main(String[] args) {
				System.out.println("Strategy Design pattern started.");
				ShoppingCart cart = new ShoppingCart(new CreditCard("1234"));

				cart.checkout(100);


				ShoppingCart cart2 = new ShoppingCart(new UPI("34@ybl"));
				cart2.checkout(370);


				System.out.println("Strategy Design pattern ended.");
		}
}
