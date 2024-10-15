package com.patterns.creational.singleton;

/**
 * This approach is same as eager initialization, This also get initialized at the time of class loading.
 * Only difference is here we can handle exception.
 */
public class StaticBlockInitialization {

		private static StaticBlockInitialization INSTANCE;

		static {
				try{
						INSTANCE = new StaticBlockInitialization();
				} catch (Exception e){
						e.printStackTrace();
				}
		}

		private StaticBlockInitialization(){

		}

		public static StaticBlockInitialization getInstance(){
				return INSTANCE;
		}
}
