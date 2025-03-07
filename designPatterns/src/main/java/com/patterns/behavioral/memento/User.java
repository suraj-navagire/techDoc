package com.patterns.behavioral.memento;

public class User {
		public static void main(String[] args) {
				System.out.println("Memento design pattern started");

				NotepadCaretaker notepadCaretaker = new NotepadCaretaker();
				Notepad notepad = new Notepad();

				notepad.write("My name is Ricky.");

				notepadCaretaker.save(notepad);

				notepad.write("I live in london.");

				notepadCaretaker.undo(notepad);

				notepad.write("I live in Dubai.");

				notepadCaretaker.save(notepad);

				System.out.println("Memento design pattern started");
		}
}
