package com.patterns.creational.singleton;

import java.util.Random;

/**
 * In eager initialization, Instance of class gets created at the time of class loading.
 * Hence, even if object of class is not required it gets created. Which is unnecessary.
 * Also, We cannot do any exception handling in this approach.
 *
 */
public class EagerInitialization {

		private static EagerInitialization INSTANCE = new EagerInitialization();

		private EagerInitialization(){
				System.out.println("Object gets created");

		}

		public static EagerInitialization getInstance(){
				System.out.println("GetInstance is called");
				return INSTANCE;
		}


		//Adding this method to show that if someone wants to use this method only then they can use this
		//without creating an object. But since we have used eager initialization here INSTANCE variable gets created.
		//This we can restrict using lazy initialization.
		public static String getClassNameOFThis(){
				return EagerInitialization.class.getName();
		}
}
