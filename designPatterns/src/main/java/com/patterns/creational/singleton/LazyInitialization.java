package com.patterns.creational.singleton;

/**
 * In this way we can avoid creation of instance at the time of class loading.
 * But this approach doesn't work for multithreaded system. In multithreaded system more than 1 instance can
 * get created.
 */
public class LazyInitialization {
		private static LazyInitialization INSTANCE;

		private LazyInitialization(){

		}

		public static LazyInitialization getInstance(){
				if(INSTANCE == null){
						INSTANCE =  new LazyInitialization();
				}

				return INSTANCE;
		}
}
