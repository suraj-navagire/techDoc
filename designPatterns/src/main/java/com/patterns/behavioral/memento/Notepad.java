package com.patterns.behavioral.memento;

/**
 * Originator : This stores its state with memento and uses it to restore this.
 */
public class Notepad {

		private String text;

		public Notepad() {
				this.text = "";
		}

		public void write(String txt){
				text = text + txt;
				System.out.println("Current state : "+text);
		}

		public NotepadMemento save(){
				return new NotepadMemento(text);
		}

		public void undo(NotepadMemento memento){
				text = memento.getText();
		}
}
