package com.patterns.behavioral.command;

/**
 * Concrete command to turn on the light.
 *
 * Consider that this class knows the bulb i.e. receiver, and it knows how to perform turn on operation.
 *
 * Client will only need to use this command with invoker.
 *
 * As you can see we will use this turn on action as an object.
 *
 * Execute method will turn on the light and undo method will turn it off.
 *
 */
public class TurnOnCommand implements Command {

		private Bulb bulb;

		public TurnOnCommand(Bulb bulb) {
				this.bulb = bulb;
		}

		@Override public void execute() {
				bulb.turnOn();
		}

		@Override public void undo() {
				bulb.turnOff();
		}
}
