package com.patterns.structural.bridge.solution;

/**
 * Concrete Implementors
 */
public class RedColor implements Color {
		@Override public void fill() {
				System.out.println("Filling Red color");
		}
}
