package com.patterns.structural.facade;

public class BankPinValidator {

		private String pin = "abc";

		public boolean isValidPin(String pin){
				if (this.pin.equals(pin)) {
						return true;
				}

				return false;
		}
}
