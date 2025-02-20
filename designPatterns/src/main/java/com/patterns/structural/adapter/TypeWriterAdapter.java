package com.patterns.structural.adapter;

/**
 * This is Adapter class.
 *
 * This class implements Target interface and wraps Adaptee.
 *
 * Purpose of this class is to make 2 different interfaces work together.
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
