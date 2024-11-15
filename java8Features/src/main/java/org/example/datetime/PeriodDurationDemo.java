package org.example.datetime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class PeriodDurationDemo {
		public static void main(String[] args) {
				System.out.println("PeriodDemo Started");
				PeriodDurationDemo demo = new PeriodDurationDemo();
				demo.example();
		}

		private void example(){
				LocalDate currentDate = LocalDate.now();

				LocalDate futureDate = LocalDate.parse("2024-12-30");

				System.out.println("Difference in days : "+ Period.between(futureDate, currentDate));

				LocalTime currentTime  = LocalTime.now();

				LocalTime time = LocalTime.parse("23:00");

				System.out.println("Time difference : "+ Duration.between(currentTime, time));
		}
}
