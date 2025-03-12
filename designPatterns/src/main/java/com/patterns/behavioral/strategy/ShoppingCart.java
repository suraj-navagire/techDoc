package com.patterns.behavioral.strategy;

/**
 * This class can be used by online shopping application to do payment.
 *
 * This class will contain strategy interface. At run time for every payment this class will get respective payment method selected by use.
 *
 * Shopping cart will deduct money at the time of checkout using available payment method.
 *
 */
public class ShoppingCart {
		private PaymentMethod paymentMethod;

		public ShoppingCart(PaymentMethod paymentMethod) {
				this.paymentMethod = paymentMethod;
		}

		public void checkout(int amount){
				paymentMethod.pay(amount);
		}

}
