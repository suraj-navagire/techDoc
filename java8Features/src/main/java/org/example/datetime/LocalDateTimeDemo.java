package org.example.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeDemo {
		public static void main(String[] args) {
				System.out.println("DateTimeDemo Started");

				LocalDateTimeDemo demo = new LocalDateTimeDemo();
				demo.example();
		}

		private void example(){
				LocalDateTime dateTime = LocalDateTime.now();
				System.out.println("Current date time : " + dateTime);

				LocalDate localDate = dateTime.toLocalDate();
				System.out.println("Current Date : "+localDate);

				LocalTime localTime = dateTime.toLocalTime();
				System.out.println("Current time : "+ localTime);

				System.out.println("Year : " + localDate.getYear()+", Month : "+localDate.getMonth()+", Day : "+localDate.getDayOfMonth());

				LocalDate futureDate = LocalDate.of(2024, 12, 20);

				System.out.println("FutureDate : "+futureDate);

				LocalTime futureTime  = LocalTime.of(23, 30);
				System.out.println("FutureTime  : "+futureTime);

				LocalDateTime stringToDate = LocalDateTime.parse("2024-11-25T21:52:39.009");

				System.out.println("Parsed Date : "+stringToDate);


				System.out.println("2024-11-25T21:52:39.009 is after current date ? : "+stringToDate.isAfter(dateTime));
		}
}
