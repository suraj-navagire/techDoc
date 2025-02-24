package com.patterns.structural.facade;

public class BankAccount {

		private String accountNumber = "12345";

		public boolean isValidAccountNumber(String accountNumber){
				if (this.accountNumber.equals(accountNumber)){
						return true;
				}

				return false;
		}
}
