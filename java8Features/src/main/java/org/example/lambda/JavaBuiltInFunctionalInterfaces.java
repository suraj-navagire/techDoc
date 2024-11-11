package org.example.lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class JavaBuiltInFunctionalInterfaces {
		public static void main(String[] args) {
				System.out.println("Java built in functional interfaces Example");

				Predicate<String> predicate = str -> "Alan".equals(str);

				System.out.println("Is name Alan : "+predicate.test("Alan"));
				System.out.println("Is name Alan : "+predicate.test("Jon"));

				Function<String, String> function = str -> str+str;
				System.out.println("Join word : "+function.apply("Alan"));

				Consumer<String> consumer = str -> System.out.println(str);
				consumer.accept("Inside consumer");

				Supplier<String> supplier = () -> "Alan";
				System.out.println("Supplier produced : "+supplier.get());
		}
}
