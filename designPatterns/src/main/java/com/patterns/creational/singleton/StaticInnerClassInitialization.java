package com.patterns.creational.singleton;

public class StaticInnerClassInitialization {

		static {
				System.out.println("outer static block");
		}
		//Static classes cannot be instantiated.
		// Also  since it is inner class it will not get loaded at unless and until gets called.
		private static class Helper{
				private static StaticInnerClassInitialization INSTANCE = new StaticInnerClassInitialization();
				static {
						System.out.println("inner static block");
				}
		}

		private StaticInnerClassInitialization(){
				System.out.println("constructor block");
		}

		public static StaticInnerClassInitialization getInstance(){
				System.out.println("getinstance block");
				return Helper.INSTANCE;
		}
}
