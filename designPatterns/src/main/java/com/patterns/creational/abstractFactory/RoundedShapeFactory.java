package com.patterns.creational.abstractFactory;

public class RoundedShapeFactory implements AbstractShapeFactory {
		@Override public Shape getShape(String type) {
				if("CIRCLE".equalsIgnoreCase(type)){
						return new Circle();
				} else {
						return new Oval();
				}
		}
}
