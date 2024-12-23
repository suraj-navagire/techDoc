package org.example.cursor;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorExample {
		public static void main(String[] args) {
				System.out.println("IteratorExample Started");

				ArrayList<Integer> list = new ArrayList<>();
				list.add(4);
				list.add(5);
				list.add(6);
				list.add(7);
				System.out.println(list);

				Iterator<Integer> iteratorCursor = list.iterator();

				while (iteratorCursor.hasNext()){
						Integer element = iteratorCursor.next();
						if(element % 3 == 0){
								iteratorCursor.remove();
						}
				}

				System.out.println(list);
		}
}
