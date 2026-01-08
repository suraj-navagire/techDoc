package com.patterns.structural.adapter;

/**
 * This is Adapter class.
 *
 * This class implements Target interface and wraps Adaptee (i.e. this creates association with adaptee interface).
 *
 * Purpose of this class is to make 2 different interfaces work together (Here DocumentPrinter and TypeWriter are 2 different interfaces).
 *
 * Why we are implementing DocumentPrinter and not TypeWriter ? Because client is working on DocumentPrinter so we have to implement that.
 *
 */
public class TypeWriterAdapter implements DocumentPrinter {

		private TypeWriter typeWriter;

		public TypeWriterAdapter(TypeWriter typeWriter) {
				this.typeWriter = typeWriter;
		}

		@Override public void print() {
				typeWriter.printDocument();
		}
}
