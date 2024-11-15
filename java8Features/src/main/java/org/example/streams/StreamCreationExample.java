package org.example.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamCreationExample {
		public static void main(String[] args) {
				System.out.println("StreamCreationExample");

				StreamCreationExample example = new StreamCreationExample();

				example.firstWay();

				example.secondWay();

				example.thirdWay();
		}

		private void firstWay(){
				System.out.println("Filtering using first way");

				String[] listOfNames = new String[]{"Alan", "John", "Steve", "Bob", "Anthony"};

				Stream<String> stream = Arrays.stream(listOfNames);

				stream.filter(r -> r.startsWith("A")).forEach(System.out::println);
		}

		private void secondWay(){
				System.out.println("Filtering using second way");

				Stream<String> stream = Stream.of("Alan", "John", "Steve", "Bob", "Anthony", "Bunny");
				stream.filter(r -> r.startsWith("B")).forEach(System.out::println);
		}


		private void thirdWay(){
				System.out.println("Filtering using third way");

				List<String> listOfNames = new ArrayList<>();

				listOfNames.add("Alan");
				listOfNames.add("John");
				listOfNames.add("Bob");
				listOfNames.add("Steve");
				listOfNames.add("Anthony");
				listOfNames.add("Bunny");

				Stream<String> stream = listOfNames.stream();
				stream.filter(r -> r.startsWith("S")).forEach(System.out::println);
		}
}
