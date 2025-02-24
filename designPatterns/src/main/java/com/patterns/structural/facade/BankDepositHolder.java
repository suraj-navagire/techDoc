package com.patterns.structural.facade;

public class BankDepositHolder {

		private int accountBalance = 50000;

		public boolean isEnoughBalance(int debitAmount){
				if (debitAmount <= accountBalance){
						return true;
				}

				return false;
		}


		public int withdraw(int debitAmount){
				accountBalance = accountBalance - debitAmount;
				return debitAmount;
		}
}
