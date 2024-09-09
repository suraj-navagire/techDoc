package org.example;

public class ThreadCreationExample3 {
		public static void main(String[] args) {
				Thread t = new Thread(new Runnable() {
						@Override public void run() {
								System.out.println("Started thread using anonymous innser class");
						}
				});

				t.start();
		}
}
