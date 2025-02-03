package org.example.map;

import java.util.HashMap;
import java.util.Map;

public class WeakHashMapExample1 {
		public static void main(String[] args) {
				System.out.println("WeakHashMapExample Started");

				Map<Burger, String> hashMap = new HashMap<>();
				Burger b1 = new Burger("120");
				Burger b2 = new Burger("150");
				hashMap.put(b1, "FirstBurger");
				hashMap.put(b2, "SecondBurger");

				System.out.println(hashMap);

				b1 = null;

				try {
						System.gc();
						Thread.sleep(3000);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				System.out.println(hashMap);
				System.out.println("WeakHashMapExample Ended");
		}
}
