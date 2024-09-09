package org.example;

public class VolatileExample {
		public static void main(String[] args) {
				Thread4 t4 = new Thread4();

				Thread t = new Thread(t4);

				t.start();

				try {
						Thread.sleep(100);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}
				t4.shutdown();

				System.out.println("Main program");
		}
}

class Thread4 implements Runnable {

		//This is resource is updated by other thread. So we should make it volatile so that thread can monitor its status.
		private volatile boolean running = true;
		@Override public void run() {
				while (running){
						try {
								Thread.sleep(10);
						} catch (InterruptedException e) {
								throw new RuntimeException(e);
						}
						System.out.println("Running");
				}
		}

		public void shutdown(){
				running = false;
		}
}
