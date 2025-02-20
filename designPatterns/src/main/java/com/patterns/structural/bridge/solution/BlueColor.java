package com.patterns.structural.bridge.solution;

/**
 * Concrete Implementors
 */
public class BlueColor implements Color{
		@Override public void fill() {
				System.out.println("Filling Blue color");
		}
}
