package com.patterns.behavioral.command;

public class Client {

		/**
		 * Consider bulb is present in room. But client or user won't touch it. instead it will use Remote Controls to perform operation.
		 */
		private static Bulb bulb = new Bulb();

		/**
		 * Commands are also predefined. Client / User will just need to use it with invoker.
		 */
		private static Command turnOn = new TurnOnCommand(bulb);

		private static Command turnOff = new TurnOffCommand(bulb);

		public static void main(String[] args) {
				System.out.println("Command pattern started");

				RemoteControl control = new RemoteControl(turnOn);
				control.pressButton();
				control.pressUndo();;

				RemoteControl control1 = new RemoteControl(turnOff);
				control1.pressButton();
				control1.pressUndo();

				System.out.println("Command pattern started");
		}
}
