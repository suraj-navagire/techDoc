package com.patterns.behavioral.strategy;

/**
 * Credit card payment strategy.
 */
public class CreditCard implements PaymentMethod{

		private String creditCardNumber;

		public CreditCard(String creditCardNumber) {
				this.creditCardNumber = creditCardNumber;
		}

		@Override public void pay(int amount) {
				System.out.println(String.format("Paying amount : %s using credit card : %s", amount, creditCardNumber));
		}
}
