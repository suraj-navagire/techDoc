package org.example;

public class JoinExample {
		public static void main(String[] args) {

				Thread3 t3 = new Thread3();

				Thread t = new Thread(t3);
				t.start();

				try {
						t.join();
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				//If we don't use join then main thread won't wait t1 thread to finish its job
				System.out.println("Printing after ");
		}
}

class Thread3 implements Runnable {

		@Override public void run() {
				for(int i=0; i< 5; i++){
						System.out.println(i);
				}
		}
}