package com.systemdesign.lld.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class PaymentFactory {

		private static final Map<PaymentType, IPaymentStrategy> strategyMap = new HashMap<>();

		public PaymentFactory() {
				strategyMap.put(PaymentType.CASH, new CashPaymentStrategy());
				strategyMap.put(PaymentType.UPI, new UPIPaymentStrategy());
		}


		public IPaymentStrategy getInstance(PaymentType type){
				return strategyMap.get(type);
		}
}
