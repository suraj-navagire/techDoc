package org.example.map;

import java.util.HashMap;

public class MapExample {
		public static void main(String[] args) {
				System.out.println("MapExample started");

				String str = "Ea";

				System.out.println(str.hashCode());

				HashMap<String, String> map = new HashMap<>();
				map.put(str, str);
				map.put(str+str, str);

				System.out.println(map);
		}
}
