package org.example.map;

import java.util.WeakHashMap;

public class WeakHashMapExample2 {
		public static void main(String[] args) {
				System.out.println("WeakHashMapExample Started");
				Burger b1 = new Burger("120");
				Burger b2 = new Burger("150");

				WeakHashMap<Burger, String> weakHashMap = new WeakHashMap<>();
				weakHashMap.put(b1, "FirstBurgerWeakMap");
				weakHashMap.put(b2, "SecondBurgerWeakMap");

				System.out.println(weakHashMap);

				b1 = null;

				try {
						System.gc();
						Thread.sleep(3000);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				System.out.println(weakHashMap);
				System.out.println("WeakHashMapExample Ended");

		}

		private static void test(Burger b3, Burger b4){


		}
}
