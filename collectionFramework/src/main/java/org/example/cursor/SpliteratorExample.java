package org.example.cursor;

import java.util.Spliterator;
import java.util.stream.Stream;

public class SpliteratorExample {
		public static void main(String[] args) {
				System.out.println("Spliterator Example started");

				Stream<String> alphabates = Stream.of("A", "B", "C", "D", "E");

				Spliterator<String> spli1 = alphabates.spliterator();

				//Splitting spli1. After this spli1 and spli2 will contain half-half elements.
				//In actual implementation we can pass bo these spli1 and spli2 to different threads to work parallel to give best performance.
				Spliterator<String> spli2 = spli1.trySplit();

				//tryAdvance works as hasNext and next method
				//When no more element it will return false and loop will end.
				while (spli1.tryAdvance(c -> System.out.print(c + " ")));

				System.out.println();

				while (spli2.tryAdvance(c -> System.out.print(c + " ")));

				System.out.println();

				System.out.println("Spliterator Example Ended");
		}
}
