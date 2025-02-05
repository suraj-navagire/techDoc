package org.example.map;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class NavigableMapExample {
		public static void main(String[] args) {
				System.out.println("NavigableMapExample Started");

				NavigableMap<Integer, String> navigableMap = new TreeMap<>();
				navigableMap.put(25, "TwentyFive");
				navigableMap.put(2, "Two");
				navigableMap.put(17, "Seventeen");
				navigableMap.put(8, "Eight");
				navigableMap.put(39, "ThirtyNine");
				navigableMap.put(6, "Six");
				navigableMap.put(20, "Twenty");

				for(Map.Entry<Integer, String> element : navigableMap.entrySet()){
						System.out.println("Key : "+element.getKey()+", Value : "+element.getValue());
				}

				System.out.println("NavigableMap Functions : ");

				System.out.println("navigableMap.ceilingEntry(10) : "+navigableMap.ceilingEntry(10));
				System.out.println("navigableMap.ceilingEntry(8) : "+navigableMap.ceilingEntry(8));

				System.out.println("navigableMap.floorEntry(10) : "+navigableMap.floorEntry(10));
				System.out.println("navigableMap.floorEntry(8) : "+navigableMap.floorEntry(8));

				System.out.println("navigableMap.higherEntry(10) : "+navigableMap.higherEntry(10));
				System.out.println("navigableMap.higherEntry(8) : "+navigableMap.higherEntry(8));

				System.out.println("navigableMap.lowerEntry(10) : "+navigableMap.lowerEntry(10));
				System.out.println("navigableMap.lowerEntry(8) : "+navigableMap.lowerEntry(8));



				System.out.println("navigableMap.ceilingKey(10) : "+navigableMap.ceilingKey(10));
				System.out.println("navigableMap.ceilingKey(8) : "+navigableMap.ceilingKey(8));

				System.out.println("navigableMap.floorKey(10) : "+navigableMap.floorKey(10));
				System.out.println("navigableMap.floorKey(8) : "+navigableMap.floorKey(8));

				System.out.println("navigableMap.higherKey(10) : "+navigableMap.higherKey(10));
				System.out.println("navigableMap.higherKey(8) : "+navigableMap.higherKey(8));

				System.out.println("navigableMap.lowerKey(10) : "+navigableMap.lowerKey(10));
				System.out.println("navigableMap.lowerKey(8) : "+navigableMap.lowerKey(8));


				System.out.println("navigableMap.headMap(10) : "+navigableMap.headMap(10));
				System.out.println("navigableMap.headMap(8) : "+navigableMap.headMap(8));


				System.out.println("navigableMap.tailMap(10) : "+navigableMap.tailMap(10));
				System.out.println("navigableMap.tailMap(8) : "+navigableMap.tailMap(8));


				System.out.println("navigableMap.subMap(8, 25) : "+navigableMap.subMap(8, 25));

				System.out.println("navigableMap.navigableKeySet() : "+navigableMap.navigableKeySet());

				System.out.println("NavigableMapExample Ended");
		}
}
