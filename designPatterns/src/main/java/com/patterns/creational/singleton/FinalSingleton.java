package com.patterns.creational.singleton;

import java.io.Serializable;

public class FinalSingleton implements Serializable, Cloneable {

		//This Holder class does not get loaded when FinalSingleton class is loaded. Its not eager initialization. It gets loaded when getInstance is called.
		//Static class is used as static class object can be created without object of outer class.
		// To instantiate a non-static inner class, we must first create an instance of the outer class. Which is not acceptable for singleton. That why we are using static inner class.
		private static class Helper {
				private static FinalSingleton INSTANCE = new FinalSingleton();
		}

		private FinalSingleton(){
				//Added this to solve reflection problem
				if(Helper.INSTANCE != null){
						throw new RuntimeException("Cannot create more than one object");
				}
		}

		public static FinalSingleton getInstance(){
				return Helper.INSTANCE;
		}

		//This method will be used to avoid creating new object at the time of serialization.
		protected Object readResolve(){
				return Helper.INSTANCE;
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
				//Suppose if someone uses this commented code or if someone extends this singleton class and overrides
				//clone method then this class won't be singleton anymore. That's why we have to override this method and
				//throw clone not supported exception.
//				return super.clone();

				throw new CloneNotSupportedException();
		}
}
