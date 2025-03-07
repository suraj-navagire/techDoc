package com.patterns.behavioral.memento;

/**
 * Stores text state.
 */
public class NotepadMemento {
		private String text;

		public NotepadMemento(String text) {
				this.text = text;
		}

		public String getText(){
				return text;
		}
}
