package com.patterns.behavioral.observer;

public interface Observable {
		public void addObserver(Observer observer);

		public void removeObserver(Observer observer);

		public void updateObservers();
}
