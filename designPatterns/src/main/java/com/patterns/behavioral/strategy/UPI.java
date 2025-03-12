package com.patterns.behavioral.strategy;

/**
 * UPI payment strategy
 */
public class UPI implements PaymentMethod{

		private String upiId;

		public UPI(String upiId) {
				this.upiId = upiId;
		}

		@Override public void pay(int amount) {
				System.out.println(String.format("Paying amount : %s using UPI : %s", amount, upiId));
		}
}
