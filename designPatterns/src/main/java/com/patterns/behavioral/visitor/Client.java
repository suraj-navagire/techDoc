package com.patterns.behavioral.visitor;


public class Client {
		public static void main(String[] args) {
				System.out.println("Visitor pattern started");

				Triangle triangle = new Triangle(4,4,5);

				Square square = new Square(5,5,10,10);

				DrawShape drawShape = new DrawShape();

				triangle.accept(drawShape);
				square.accept(drawShape);

				System.out.println("Visitor pattern ended");
		}
}
