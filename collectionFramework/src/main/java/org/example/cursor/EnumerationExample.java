package org.example.cursor;

import java.util.Enumeration;
import java.util.Vector;

public class EnumerationExample {
		public static void main(String[] args) {
				System.out.println("EnumerationExample Started");
				Vector v = new Vector();
				v.addElement(1);
				v.addElement(2);

				Enumeration enumCursor = v.elements();
				while (enumCursor.hasMoreElements()){
						System.out.println(enumCursor.nextElement());
				}
		}
}
