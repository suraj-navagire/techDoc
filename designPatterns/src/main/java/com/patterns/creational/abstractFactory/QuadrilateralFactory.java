package com.patterns.creational.abstractFactory;

public class QuadrilateralFactory implements AbstractShapeFactory{
		@Override public Shape getShape(String type) {
				if ("SQUARE".equalsIgnoreCase(type)){
						return new Square();
				} else {
						return new Rectangle();
				}
		}
}
