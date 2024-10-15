package com.patterns.creational.factoryPattern;

public class ComputerFactory {

		public static Computer getInstance(String type, String ram, String hDD, String core){

				if("PC".equals(type)){
						return new PC(ram, hDD, core);
				} else {
						return  new Server(ram, hDD, core);
				}
		}
}
