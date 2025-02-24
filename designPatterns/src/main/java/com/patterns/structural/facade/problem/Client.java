package com.patterns.structural.facade.problem;

import com.patterns.structural.facade.BankAccount;
import com.patterns.structural.facade.BankDepositHolder;
import com.patterns.structural.facade.BankPinValidator;

/**
 * Without Facade client will end-up calling too many API's since API's are poorly designed and exposed.
 *
 * In this example bank has poorly designed the API's. Client has to perform 4 tasks to withdraw amount.
 * This can be minimized to 1 task using facade layer.
 */
public class Client {
		public static void main(String[] args) {
				System.out.println("Without Facade design pattern started");

				//Step 1: Validate account number
				BankAccount account = new BankAccount();
				boolean isValidAccount = account.isValidAccountNumber("12345");
				System.out.println("Bank account verification status : "+isValidAccount);

				if(!isValidAccount){
						return;
				}

				//Step 2: Validate Pin
				BankPinValidator pinValidator = new BankPinValidator();
				boolean isValidPin = pinValidator.isValidPin("abc");
				System.out.println("Bank pin verification status : "+isValidPin);

				if(!isValidPin){
						return;
				}

				//Step 3: Validate balance
				BankDepositHolder depositHolder = new BankDepositHolder();
				boolean isEnoughBalance = depositHolder.isEnoughBalance(3000);
				System.out.println("Does account have enough balance : "+isEnoughBalance);

				if(!isEnoughBalance){
						return;
				}

				//Step 4: Withdraw
				int amount = depositHolder.withdraw(3000);
				System.out.println("Amount withdraw : "+amount);

				System.out.println("Without Facade design pattern Ended");

		}
}
