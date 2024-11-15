package org.example.streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapDemo {
		public static void main(String[] args) {
				System.out.println("FlatMap started");

				FlatMapDemo demo = new FlatMapDemo();

				demo.flatMap();
		}

		private void flatMap(){
				Stream<String> names = Stream.of("Alan", "John", "Bob");
				List<String> words = names.flatMap(r -> Stream.of(r.split(""))).collect(Collectors.toList());

				words.forEach(System.out::println);
		}
}
