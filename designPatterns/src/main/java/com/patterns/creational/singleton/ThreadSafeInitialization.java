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
										INSTANCE = new ThreadSafeInitialization();
								}
						}
				}

				return INSTANCE;
		}
}
