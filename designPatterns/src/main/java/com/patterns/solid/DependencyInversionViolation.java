package com.patterns.solid;

public class DependencyInversionViolation {
		public static void main(String[] args) {
				//Driver is tightly coupled with car and bike
				Driver drive = new Driver();
				drive.driveCar();
				drive.rideMotorcycle();
		}
}

interface IVehicle {
		void start();

		void stop();
}

class Car implements IVehicle {
		public void start() {
				// Car-specific implementation of Start()
		}

		public void stop() {
				// Car-specific implementation of Stop()
		}
}

class Motorcycle implements IVehicle {
		public void start() {
				// Motorcycle-specific implementation of Start()
		}

		public void stop() {
				// Motorcycle-specific implementation of Stop()
		}
}

//This is high level module, and it is directly dependent on low level module car and motorcycle directly.
class Driver {
		private Car car;
		private Motorcycle motorcycle;

		public Driver() {
				this.car = new Car();
				this.motorcycle = new Motorcycle();
		}

		public void driveCar() {
				car.start();
				car.stop();
		}

		public void rideMotorcycle() {
				motorcycle.start();
				motorcycle.stop();
		}
}