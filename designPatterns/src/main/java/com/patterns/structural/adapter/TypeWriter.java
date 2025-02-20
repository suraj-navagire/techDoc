package com.patterns.structural.adapter;

/**
 * This is legacy class i.e. Adaptee class which does not follow standard of modern printer.
 *
 * The existing class or interface that needs to be adapted (the one with the incompatible interface).
 *
 */
public class TypeWriter {
		void printDocument(){
				System.out.println("Typing all the text and printing.");
		}
}
