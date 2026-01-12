package com.systemdesign.lld.parkinglot;

import java.time.Duration;
import java.time.LocalDateTime;

public class BikeFeeCalculationStrategy implements IFeeCalculationStrategy{

		private static final int FEES = 10;
		@Override public long calculateFee(Ticket ticket) {
				LocalDateTime entryTime = ticket.getEntryTime();
				long hour = Duration.between(entryTime, LocalDateTime.now()).toHours();
				return hour == 0 ? FEES : hour * FEES;
		}
}
