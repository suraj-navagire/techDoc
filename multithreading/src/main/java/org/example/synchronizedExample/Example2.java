package org.example.synchronizedExample;

public class Example2 {
		//This is shared resource
		private int count = 0;

		public static void main(String[] args) {
				Example2 e = new Example2();
				e.doWork();
		}

		private void doWork(){

				Thread t1 = new Thread(new Runnable() {
						@Override public void run() {
								for (int i=0; i<10000; i++)
										increment();
						}
				});


				Thread t2 = new Thread(new Runnable() {
						@Override public void run() {
								for (int i=0; i<10000; i++)
										increment();
						}
				});

				t1.start();
				t2.start();

				try {
						t1.join();
						t2.join();
				} catch (InterruptedException e) {
						throw new RuntimeException(e);
				}

				//Ideally output should be 20k but we never get this since increment() is not synchronized.
				System.out.println("Count : "+count);
		}


		private void increment(){
				count++;
		}
}
