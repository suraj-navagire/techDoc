package org.example.lambda;

public class AnonymousInnerClassExample {
		public static void main(String[] args) {
				//Here Car is a functional interface. We are writing and anonymous inner class to provide an implementation.
				Car sedan = new Car() {
						@Override public void drive() {
								System.out.println("Driving sedan using anonymous inner class");
						}
				};


				sedan.drive();

				//Here we will use lambda for the same.
				Car sedan2 = () -> {
						System.out.println("Driving sedan using lambda");
				};

				sedan2.drive();

		}
}
