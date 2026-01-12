package com.systemdesign.lld.parkinglot;

public class CashPaymentStrategy implements IPaymentStrategy{
		@Override public boolean pay(long amount) {
				System.out.println("Paying using cash "+ amount);
				return true;
		}
}
