package com.patterns.behavioral.command;

/**
 * This is receiver class.
 *
 * This receiver supports 2 tasks turnOff and turnOn, so it will have 2 Command classes TurnOff and TurnOn
 */
public class Bulb {
		private boolean status;

		public void turnOn(){
				status = true;
				System.out.println("Turning on");
		}

		public void turnOff(){
				status = false;
				System.out.println("Turning off");
		}
}