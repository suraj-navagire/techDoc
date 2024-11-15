package org.example.streams;

import java.util.stream.Stream;

public class ReduceDemo {
		public static void main(String[] args) {
				System.out.println("ReduceDemo Started");
				ReduceDemo demo = new ReduceDemo();

				demo.sumElements();
		}

		private void sumElements(){
				Stream<Integer> numbers = Stream.of(1, 4, 9, 8);

				Integer total = numbers.reduce((n1, n2) -> n1 + n2).orElse(0);

				System.out.println(total);
		}
}
