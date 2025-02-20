package com.patterns.structural.adapter;

/**
 *
 */
public class ModernPrinter implements DocumentPrinter {
		@Override public void print() {
				System.out.println("Printing Document Automatically using printer");
		}
}
