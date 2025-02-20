package com.patterns.structural.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * This is client code which works on Target Interface i.e. @{@link DocumentPrinter}
 */
public class Client {
		public static void main(String[] args) {
				System.out.println("Client started printing using all printers\n");

				List<DocumentPrinter> allPrinters = getAllPrinter();

				allPrinters.add(getLegacyPrinter());

				for (DocumentPrinter printer : allPrinters) {
						printer.print();
				}
		}

		private static List<DocumentPrinter> getAllPrinter(){
				List<DocumentPrinter> allPrinter = new ArrayList<>();

				allPrinter.add(new ModernPrinter());
				allPrinter.add(new Xerox());

				return allPrinter;
		}

		private static DocumentPrinter getLegacyPrinter(){
				return new TypeWriterAdapter(new TypeWriter());
		}
}
