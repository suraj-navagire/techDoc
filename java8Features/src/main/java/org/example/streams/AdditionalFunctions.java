package org.example.streams;

import java.util.stream.Stream;

public class AdditionalFunctions {
		public static void main(String[] args) {
				System.out.println("AdditionalFunctions Started");

				AdditionalFunctions functions = new AdditionalFunctions();
				functions.limit();

				functions.distinct();

				functions.skip();

				functions.max();

				functions.min();
		}

		private void limit(){
				System.out.println("Inside limit");
				Stream<Integer> numbers = Stream.of(5, 9, 3, 444, 8);

				Stream<Integer> newNumbers = numbers.limit(2);

				newNumbers.forEach(System.out::println);
		}

		private void distinct(){
				System.out.println("inside distinct");
				Stream<Integer> numbers = Stream.of(5, 5, 3, 3, 444, 8);

				Stream<Integer> newNumbers = numbers.distinct();

				newNumbers.forEach(System.out::println);

		}

		private void skip(){
				System.out.println("inside skip");
				Stream<Integer> numbers = Stream.of(5, 5, 3, 3, 444, 8);

				Stream<Integer> newNumbers = numbers.skip(2);

				newNumbers.forEach(System.out::println);

		}

		private void max(){
				System.out.println("inside max");
				Stream<Integer> numbers = Stream.of(5, 5, 3, 3, 444, 8);

				Integer max = numbers.max(Integer::compareTo).orElse(0);

				System.out.println("Max : "+max);
		}

		private void min(){
				System.out.println("inside min");
				Stream<Integer> numbers = Stream.of(5, 5, 3, 3, 444, 8);

				Integer min = numbers.min(Integer::compareTo).orElse(0);

				System.out.println("Min : "+min);
		}
}
