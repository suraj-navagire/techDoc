package com.patterns.solid;

public class OpenClosedCorrect {
		public static void main(String[] args) {
				IPaymentProcessor creditCardPaymentProcessor = new CreditCardPaymentProcessor();
				creditCardPaymentProcessor.process();

				//New payment processing added without changing existing classes.
				IPaymentProcessor phonePePaymentProcessor = new PhonePePaymentProcessor();
				phonePePaymentProcessor.process();
		}
}


interface IPaymentProcessor {
		void process();
}

class CreditCardPaymentProcessor implements IPaymentProcessor{

		@Override public void process() {
				System.out.println("Credit card payment Processing");
		}
}

//New payment method added without changing existing class
class PhonePePaymentProcessor implements IPaymentProcessor{

		@Override public void process() {
				System.out.println("Phone payment processing");
		}
}