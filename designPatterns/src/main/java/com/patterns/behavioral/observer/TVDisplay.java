package com.patterns.behavioral.observer;

public class TVDisplay implements Observer{
		private String screen;

		public TVDisplay(String screen) {
				this.screen = screen;
		}

		@Override public void update(int temp) {
				System.out.println(screen+" updated temperature : "+temp);
		}
}
