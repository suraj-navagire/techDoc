package org.example.synchronizedExample;

public class Example1 {

		//This is shared resource
		private int count = 0;

		public static void main(String[] args) {
				Example1 e = new Example1();
				e.doWork();
		}

		private void doWork(){

				Thread t1 = new Thread(new Runnable() {
						@Override public void run() {
								for (int i=0; i<10000; i++)
								count++;
						}
				});


				Thread t2 = new Thread(new Runnable() {
						@Override public void run() {
								for (int i=0; i<10000; i++)
								count++;
						}
				});

				t1.start();
				t2.start();

				try {
						t1.join();
						t1.join();
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}


				//Ideally output should be 20k but we never get this since count ++ operation is not synchronized.
				System.out.println("Count : "+count);
		}
}
