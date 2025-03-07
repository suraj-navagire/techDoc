package com.patterns.behavioral.memento;

import java.util.Stack;

public class NotepadCaretaker {
		private Stack<NotepadMemento> history = new Stack<>();

		public void save(Notepad notepad){
				history.push(notepad.save());
		}

		public void undo(Notepad notepad){
				if(!history.isEmpty()){
						notepad.undo(history.pop());
				}
		}
}
