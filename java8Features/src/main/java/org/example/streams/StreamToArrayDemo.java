package org.example.streams;

import java.util.stream.Stream;

public class StreamToArrayDemo {
		public static void main(String[] args) {
				System.out.println("ListToArrayDemo Started");

				Stream<Integer> numbers = Stream.of(1, 2, 3, 4);

				Integer[] numberArray = numbers.toArray(Integer[]::new);

				for(Integer n : numberArray){
						System.out.println(n);
				}
				
		}
}
