package com.patterns.structural.facade.solution;

public class Client {
		public static void main(String[] args) {
				System.out.println("Facade design pattern started");

				BankFacade facade = new BankFacade();
				int amount = facade.validateAndWithdraw("1234", "abc", 3000);

				System.out.println("Amount withdraw : "+amount);


				System.out.println("Facade design pattern started");


		}
}
