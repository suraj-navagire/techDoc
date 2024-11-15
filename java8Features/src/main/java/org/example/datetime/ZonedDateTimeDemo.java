package org.example.datetime;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeDemo {
		public static void main(String[] args) {
				System.out.println("ZonedDateTimeDemo Started");
				ZonedDateTimeDemo demo = new ZonedDateTimeDemo();
				demo.example();
		}

		private void example(){
				ZonedDateTime dateTime = ZonedDateTime.now();
				System.out.println("Current zoned time : "+ dateTime);

				ZoneId europe = ZoneId.of("Europe/Paris");
				System.out.println("Europe zone id : "+ europe);

				System.out.println("System default zone id : "+ ZoneId.systemDefault());

				ZonedDateTime europeDateTime = ZonedDateTime.now(europe);
				System.out.println("Europe Date time : "+ europeDateTime);

				System.out.println("Europe Time  : "+ europeDateTime.toLocalTime());
		}
}
