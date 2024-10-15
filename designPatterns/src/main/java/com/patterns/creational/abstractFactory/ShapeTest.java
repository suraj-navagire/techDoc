package com.patterns.creational.abstractFactory;

public class ShapeTest {
		public static void main(String[] args) {
				System.out.println("ShapeTest started");
				String type = "SQUARE";

				AbstractShapeFactory factory = AbstractShapeFactory.getFactory(type);

				Shape shape = factory.getShape(type);

				shape.drawShape();

				type = "CIRCLE";

				factory = AbstractShapeFactory.getFactory(type);

				shape = factory.getShape(type);

				shape.drawShape();
		}
}
