package org.example;

public class InterruptExample {
		public static void main(String[] args) {
				System.out.println("InterruptExample started");

				Thread t1 = new Thread(() -> {
						int count =0;
					for(int i=0; i<1E9; i++){
							if(Thread.currentThread().isInterrupted()){
									System.out.println("Thread interrupted");
									break;
							}
							count++;
						}

					System.out.println("Thread finished");
				});

				t1.start();

				try {
						Thread.sleep(1000);
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				t1.interrupt();

				try {
						t1.join();
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				System.out.println("Main Thread ended");
		}
}
