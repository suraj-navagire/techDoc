package com.patterns.solid;

public class DependencyInversionCorrect {
		public static void main(String[] args) {

				//Driver is loosely coupled with car.
				Driver1 driver = new Driver1(new Car1());
				driver.drive();
		}
}


interface IVehicle1 {
		void start();

		void stop();
}

class Car1 implements IVehicle {
		public void start() {
				// Car-specific implementation of Start()
		}

		public void stop() {
				// Car-specific implementation of Stop()
		}
}

class Motorcycle1 implements IVehicle {
		public void start() {
				// Motorcycle-specific implementation of Start()
		}

		public void stop() {
				// Motorcycle-specific implementation of Stop()
		}
}

//This is high level module, and it is dependent on abstraction. Not directly dependent.
class Driver1 {

		private IVehicle vehicle;

		public Driver1(IVehicle vehicle) {
				this.vehicle = vehicle;
		}

		public void drive() {
				vehicle.start();
				vehicle.stop();
		}
}
