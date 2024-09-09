package org.example;

public class ThreadCreationExample2 {
		public static void main(String[] args) {
				Thread2 t2 = new Thread2();

				Thread t = new Thread(t2);

				t.start();
		}
}


class Thread2 implements Runnable {

		@Override public void run() {
				System.out.println("Created thread using Runnable");
		}
}