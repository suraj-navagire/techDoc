package com.patterns.structural.facade.solution;

import com.patterns.structural.facade.BankAccount;
import com.patterns.structural.facade.BankDepositHolder;
import com.patterns.structural.facade.BankPinValidator;

/**
 * Facade class which calls different subsystems/API's on behalf of client. It hides the complexity and expose the simplified API.
 */
public class BankFacade {

		private BankAccount bankAccount;

		private BankPinValidator bankPinValidator;

		private BankDepositHolder bankDepositHolder;

		public BankFacade() {
				this.bankAccount = new BankAccount();
				this.bankPinValidator = new BankPinValidator();
				this.bankDepositHolder = new BankDepositHolder();
		}

		public int validateAndWithdraw(String accountNumber, String pin, int debiAmount){
				boolean isValidAccount = bankAccount.isValidAccountNumber("12345");
				System.out.println("Bank account verification status : "+isValidAccount);

				if(!isValidAccount){
						return 0;
				}

				boolean isValidPin = bankPinValidator.isValidPin("abc");
				System.out.println("Bank pin verification status : "+isValidPin);

				if(!isValidPin){
						return 0;
				}


				boolean isEnoughBalance = bankDepositHolder.isEnoughBalance(3000);
				System.out.println("Does account have enough balance : "+isEnoughBalance);

				if(!isEnoughBalance){
						return 0;
				}

				int amount = bankDepositHolder.withdraw(3000);

				return amount;

		}
}
