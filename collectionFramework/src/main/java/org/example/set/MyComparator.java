package org.example.set;

import java.util.Comparator;

public class MyComparator implements Comparator<Integer> {
		@Override public int compare(Integer o1, Integer o2) {
				//Here we want integer in descending order.
				if(o1 < o2){
						return 1;
				} else if (o1 > o2){
						return -1;
				}
				return 0;
		}
}
