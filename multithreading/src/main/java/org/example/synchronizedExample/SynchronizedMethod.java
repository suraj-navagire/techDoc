package org.example.synchronizedExample;

public class SynchronizedMethod {
		//This is shared resource
		private int count = 0;

		public static void main(String[] args) {
				SynchronizedMethod e = new SynchronizedMethod();
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

				//Since we have made method synchronized we will get correct output.
				System.out.println("Count : "+count);
		}


		//We can solve problem by making method synchronized
		private synchronized void increment(){
				count++;
		}
}
