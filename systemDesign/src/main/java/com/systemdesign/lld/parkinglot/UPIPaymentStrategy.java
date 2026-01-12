package com.systemdesign.lld.parkinglot;

public class UPIPaymentStrategy implements IPaymentStrategy{
		@Override public boolean pay(long amount) {
				System.out.println("UPI payment strategy : "+amount);
				return true;
		}
}
