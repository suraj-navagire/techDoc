package org.example.lambda;

import java.util.stream.Stream;

public class MethodReferenceExample {

		public MethodReferenceExample(){

		}

		public MethodReferenceExample(String str){
				System.out.println(str);
		}


		public static void main(String[] args) {
				System.out.println("MethodReferenceExample started");
				try{
						MethodReferenceExample example = new MethodReferenceExample();
						example.staticMethodReference();
						example.referenceToInstanceMethod();
						example.referenceToInstanceMethodArbitrary();
						example.constructorReference();
				} catch (Exception e){
						e.printStackTrace();
				}

		}

		private void staticMethodReference(){
				System.out.println("Inside staticMethodReference");

				Stream<String> stream = Stream.of("A", "B", "C");

				stream.forEach(r -> MethodReferenceExample.print(r));

				stream = Stream.of("A", "B", "C");
				//Above code can be written using method reference
				stream.forEach(MethodReferenceExample::print);
		}

		private static void print(String str){
				System.out.println(str);
		}

		private void referenceToInstanceMethod(){
				System.out.println("Inside referenceToInstanceMethod");
				Stream<String> stream = Stream.of("A", "B", "C");
				MethodReferenceExample me = new MethodReferenceExample();

				stream.forEach(r -> me.printInstance(r));

				//Above code can be written using method reference
				stream = Stream.of("A", "B", "C");

				stream.forEach(me::printInstance);

		}

		private void printInstance(String str){
				System.out.println(str);
		}

		private void referenceToInstanceMethodArbitrary(){
				System.out.println("Inside referenceToInstanceMethodArbitrary");
				Stream<String> stream = Stream.of("A", "B", "C");
				MethodReferenceExample me = new MethodReferenceExample();

				stream.sorted((a, b) -> a.compareTo(b));

				//Above code can be written using method reference
				stream = Stream.of("A", "B", "C");

				stream.sorted(String::compareTo);
		}


		private void constructorReference(){
				System.out.println("Inside constructorReference");
				Stream<String> stream = Stream.of("A", "B", "C");

				stream.forEach(r -> new MethodReferenceExample(r));

				//Above code can be written using method reference
				stream = Stream.of("A", "B", "C");
				stream.forEach(MethodReferenceExample::new);
		}

}
