package com.patterns.structural.decorator;

/**
 * This is a component interface. This will be implemented by concrete class and decorator abstract class. Additional functionalities will
 * be provided by child of decorator classes.
 */
public interface Coffee {
		int getCost();

		void prepare();
}
