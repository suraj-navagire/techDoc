package com.systemdesign.lld.parkinglot;

public class ExitGate {
		private String gateId;

		private static final FeeCalculationFactory FEE_CALCULATION_FACTORY = new FeeCalculationFactory();

		private static final PaymentFactory PAYMENT_FACTORY = new PaymentFactory();

		public ExitGate(String gateId) {
				this.gateId = gateId;
		}

		public boolean processExit(Ticket ticket, PaymentType paymentType){
			VehicleType vehicleType = ticket.getVehicle().getVehicleType();

			IFeeCalculationStrategy strategy = FEE_CALCULATION_FACTORY.getInstance(vehicleType);
			long fees =	strategy.calculateFee(ticket);

			IPaymentStrategy paymentStrategy = PAYMENT_FACTORY.getInstance(paymentType);
			boolean isSuccess = paymentStrategy.pay(fees);

			if(isSuccess){
					ticket.getSlot().unParkVehicle();
			}

			System.out.println("Vehicle : "+ticket.getVehicle().getRegistrationNumber() + " exited from : "+gateId);
			return isSuccess;
		}

		public String getGateId() {
				return gateId;
		}
}
