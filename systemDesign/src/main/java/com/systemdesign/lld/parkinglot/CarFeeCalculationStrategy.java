package com.systemdesign.lld.parkinglot;

import java.time.Duration;
import java.time.LocalDateTime;

public class CarFeeCalculationStrategy implements IFeeCalculationStrategy{

		private static final int FEES = 20;
		@Override public long calculateFee(Ticket ticket) {
				long hour = Duration.between(ticket.getEntryTime(), LocalDateTime.now()).toHours();
				return hour == 0 ? FEES : hour * FEES;
		}
}
