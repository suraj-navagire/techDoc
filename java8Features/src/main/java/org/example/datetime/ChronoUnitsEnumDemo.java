package org.example.datetime;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ChronoUnitsEnumDemo {
		public static void main(String[] args) {
				System.out.println("ChronoUnitsEnum Started");
				ChronoUnitsEnumDemo demo = new ChronoUnitsEnumDemo();
				demo.example();
		}

		private void example(){
				LocalDateTime localDateTime = LocalDateTime.now();
				System.out.println("Current Date time : "+localDateTime);
				System.out.println("Date after 177 day : "+localDateTime.plus(177, ChronoUnit.DAYS));

				System.out.println("Date after 10 months : "+ localDateTime.plus(10, ChronoUnit.MONTHS));

				System.out.println("Date time after 10 years :" + localDateTime.plus(10, ChronoUnit.YEARS));
		}
}
