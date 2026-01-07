package com.patterns.creational.singleton;

/**
 * Here we are using double-checked lock to initialize clas.
 * This approach is not reliable for some times. Because of multiprocessor systems and JIT compilers reordering behaviour.
 * We can use volatile keyword for variable.
 */
public class ThreadSafeInitialization {
		private static ThreadSafeInitialization INSTANCE;

		private ThreadSafeInitialization(){

		}

		public static ThreadSafeInitialization getInstance(){
				if(INSTANCE == null){

						synchronized (ThreadSafeInitialization.class){
								if(INSTANCE == null){
										//Issue with this approach is :
										//This step is actually done in 3 steps. 1. Allocate memory for object. 2. Initialize object. 3 assign memory location to reference.
										//JVM/CPU may reorder it. Suppose it reordered in 1 -> 3 -> 2 then reference will be assigned to reference before initializing object.
										//Because of this for other threads INSTANCE != null so they will start using that object which is still not fully initialized.
										//To solve this we can use volatile keyword for INSTANCE. So JVM will not reorder steps. All threads will get correct instance.
										INSTANCE = new ThreadSafeInitialization();
								}
						}
				}

				return INSTANCE;
		}
}
