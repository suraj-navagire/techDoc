package org.example.map;

public class Burger {
		private String price;

		public Burger(String price) {
				this.price = price;
		}

		@Override public String toString() {
				return "Burger{" + "price='" + price + '\'' + '}';
		}

		@Override
		public void finalize(){
				System.out.println("Burger with price : "+price+" is garbage collected");

		}
}
