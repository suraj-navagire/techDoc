package com.patterns.creational.singleton;

import java.io.Serializable;

public class FinalSingleton implements Serializable, Cloneable {

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
