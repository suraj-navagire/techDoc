package org.example.cursor;

import java.util.ArrayList;
import java.util.ListIterator;

public class ListIteratorExample {
		public static void main(String[] args) {
				System.out.println("ListIteratorExample Started");
				ArrayList<Integer> list = new ArrayList<>();
				list.add(5);
				list.add(10);
				list.add(15);
				list.add(17);
				list.add(20);
				System.out.println(list);

				ListIterator<Integer> listIteratorCursor = list.listIterator();
				while (listIteratorCursor.hasNext()){
						Integer element = listIteratorCursor.next();

						if(element % 2 == 0){
								listIteratorCursor.add(element+1);
						}

						if(element % 3 == 0){
								listIteratorCursor.remove();
						}

						if(element == 17){
								listIteratorCursor.set(177);
						}
				}

				System.out.println(list);

		}
}
