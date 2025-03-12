package com.patterns.behavioral.strategy;

/**
 * Debit card payment strategy.
 */
public class DebitCard implements PaymentMethod {

		private String debitCardNumber;

		public DebitCard(String debitCardNumber) {
				this.debitCardNumber = debitCardNumber;
		}

		@Override public void pay(int amount) {
				System.out.println(String.format("Paying amount : %s using debit card : %s", amount, debitCardNumber));
		}
}
