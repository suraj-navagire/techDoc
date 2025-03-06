package com.patterns.behavioral.observer;

public class Application {
		public static void main(String[] args) {
				System.out.println("Observer pattern started");
				Observer screen1 = new TVDisplay("Screen 1");
				Observer screen2 = new TVDisplay("Screen 2");

				WeatherStation subject = new WeatherStation();
				subject.addObserver(screen1);
				subject.addObserver(screen2);

				subject.updateTemperature(10);
				subject.updateTemperature(20);

				System.out.println("Observer pattern started");
		}
}
