package com.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Weather is monitored by many systems.
 */
public class WeatherStation implements Observable{
		private int temperature;

		private List<Observer> observers = new ArrayList<>();

		public void updateTemperature(int temp){
				this.temperature = temp;
				updateObservers();
		}

		@Override public void addObserver(Observer observer) {
				observers.add(observer);
		}

		@Override public void removeObserver(Observer observer) {
				observers.remove(observer);
		}

		@Override public void updateObservers() {
				observers.forEach(observer -> observer.update(temperature));
		}
}
