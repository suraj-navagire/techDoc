package org.example;

public class ThreadCreationExample1 {
		public static void main(String[] args) {
				Thread1 t1 = new Thread1();
				t1.start();
		}
}


class Thread1 extends Thread {

		@Override
		public void run(){
				System.out.println("Created thread by extending Thread class");
		}
}