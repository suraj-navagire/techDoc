package com.patterns.solid;

public class OpenClosedViolation {
		public static void main(String[] args) {

				PaymentProcessor processor = new PaymentProcessor();
				processor.processCreditCardPayment();

				//New payment processor added
				processor.processPhonepePayment();
		}
}


class PaymentProcessor{
		//Suppose this is existing method,
		void processCreditCardPayment(){
				System.out.println("Processing credit card payment");
		}

		//Suppose in future there is requirement to add phonepe paymnet processor. If we add in this class then it will be
		//violation of open/closed principle
		void processPhonepePayment(){
				System.out.println("Processing phonepe payment");
		}
}

