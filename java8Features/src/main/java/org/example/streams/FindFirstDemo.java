package org.example.streams;

import java.util.stream.Stream;

public class FindFirstDemo {
		public static void main(String[] args) {
				System.out.println("FindFirstDemo started");

				FindFirstDemo demo = new FindFirstDemo();
				demo.findFirst();
		}

		private void findFirst(){
				Stream<Integer> numbers = Stream.of(1, 5, 7, 8);

				System.out.println("First Element : "+numbers.findFirst().orElse(0));

				numbers = Stream.of(1, 5, 7, 8);

				System.out.println("Any Element : "+numbers.findAny().orElse(0));
		}
}
