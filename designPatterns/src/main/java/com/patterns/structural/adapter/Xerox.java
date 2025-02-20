package com.patterns.structural.adapter;

public class Xerox implements DocumentPrinter {

		@Override public void print() {
				System.out.println("Printing document automatically using xerox.");
		}
}
