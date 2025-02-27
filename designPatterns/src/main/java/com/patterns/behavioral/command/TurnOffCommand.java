package com.patterns.behavioral.command;

/**
 * Concrete command to turn off the light.
 *
 * Consider that this class knows the bulb i.e. receiver, and it knows how to perform turn off operation.
 *
 * Client will only need to use this command with invoker.
 *
 * As you can see we will use this turn off action as an object.
 *
 * Execute method will turn off the light and undo method will turn it onn.
 *
 */
public class TurnOffCommand implements Command{

		private Bulb bulb;

		public TurnOffCommand(Bulb bulb) {
				this.bulb = bulb;
		}

		@Override public void execute() {
				bulb.turnOff();
		}

		@Override public void undo() {
				bulb.turnOn();
		}
}