package org.example.optional;

import java.util.Optional;

public class OptionalDemo {
		public static void main(String[] args) {
				System.out.println("OptionalDemo Started");

				OptionalDemo demo = new OptionalDemo();
				demo.example();
		}

		private void example(){
				Integer[] number = new Integer[10];
				number[0] = 2;
				number[1] = 5;
				number[2] = 6;

				Optional<Integer> optionalNumber = Optional.ofNullable(number[1]);

				System.out.println("Value at index 1 : " +optionalNumber.orElse(0));

				optionalNumber = Optional.ofNullable(number[8]);
				System.out.println("Value at index 8 : " +optionalNumber.orElse(0));

		}
}
