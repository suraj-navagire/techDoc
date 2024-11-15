package org.example.streams;

import java.util.Arrays;
import java.util.stream.Stream;

public class MatchDemo {
		public static void main(String[] args) {
				System.out.println("MatchDemo Started");

				MatchDemo demo = new MatchDemo();

				Integer[] numbers = new Integer[]{5, 10, 15, 12};

				demo.anyMatch(numbers);
				demo.allMatch(numbers);
				demo.noneMatch(numbers);
		}

		private void anyMatch(Integer[] numbers){
				Stream<Integer> numbersStream = Arrays.stream(numbers);

				System.out.println("is divisible by 5 "+numbersStream.anyMatch(n -> n % 5 == 0));

				numbersStream = Arrays.stream(numbers);

				System.out.println("is divisible by 12 "+numbersStream.anyMatch(n -> n % 12 == 0));

				numbersStream = Arrays.stream(numbers);
				System.out.println("is divisible by 20 "+numbersStream.anyMatch(n -> n % 20 == 0));

		}

		private void allMatch(Integer[] numbers){
				Stream<Integer> numbersStream = Arrays.stream(numbers);

				System.out.println("is all divisible by 1 "+numbersStream.allMatch(n -> n % 1 == 0));

				numbersStream = Arrays.stream(numbers);

				System.out.println("is all divisible by 5 "+numbersStream.allMatch(n -> n % 5 == 0));
		}

		private void noneMatch(Integer[] numbers){
				Stream<Integer> numbersStream = Arrays.stream(numbers);

				System.out.println("none divisible by 12 "+numbersStream.noneMatch(n -> n % 12 == 0));

				numbersStream = Arrays.stream(numbers);

				System.out.println("none divisible by 20 "+numbersStream.noneMatch(n -> n % 20 == 0));
		}

}
