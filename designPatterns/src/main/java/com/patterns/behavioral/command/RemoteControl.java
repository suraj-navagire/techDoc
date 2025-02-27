package com.patterns.behavioral.command;

/**
 * Remote control class. This will contain the command.
 */
public class RemoteControl {
		private Command command;

		public RemoteControl(Command command) {
				this.command = command;
		}

		public void pressButton(){
				command.execute();
		}

		public void pressUndo(){
				command.undo();
		}
}
