package com.patterns.creational.abstractFactory;

public interface AbstractShapeFactory {

		public static AbstractShapeFactory getFactory(String type){
				if("SQUARE".equalsIgnoreCase(type) || "RECTANGLE".equalsIgnoreCase(type)){
						return new QuadrilateralFactory();
				} else {
						return  new RoundedShapeFactory();
				}
		}

		public Shape getShape(String type);
}
