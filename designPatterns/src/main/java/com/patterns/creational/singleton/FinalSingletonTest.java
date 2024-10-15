package com.patterns.creational.singleton;

import java.io.*;
import java.lang.reflect.Constructor;

public class FinalSingletonTest {
		public static void main(String[] args) {
				System.out.println("Test started");

				FinalSingleton singleton1 = FinalSingleton.getInstance();

				FinalSingleton singleton2 = FinalSingleton.getInstance();

				if (singleton2 == singleton1){
						System.out.println("Same object");
						reflectionTest(singleton1);
						serializationTest(singleton1);
						cloningTest(singleton1);
				} else {
						System.out.println("Different object");
				}
		}

		private static void reflectionTest(FinalSingleton singleton){
				FinalSingleton instance = singleton;
				try{
						Constructor<?>[] constructors = FinalSingleton.class.getDeclaredConstructors();

						for (Constructor t : constructors){
								t.setAccessible(true);
								instance = (FinalSingleton)t.newInstance();
								break;
						}
				} catch (Exception e){
						System.out.println("Exception occurred while doing reflection");
				}

				if(singleton == instance){
						System.out.println("Same object After reflection");
				} else {
						System.out.println("Different object After reflection");
				}

		}

		private static void serializationTest(FinalSingleton singleton){
				try {
						ObjectOutput output = new ObjectOutputStream(new FileOutputStream("singleton.text"));
						output.writeObject(singleton);
						output.close();

						ObjectInput input = new ObjectInputStream(new FileInputStream("singleton.text"));
						FinalSingleton newInstance = (FinalSingleton) input.readObject();

						if(singleton == newInstance){
								System.out.println("Same object After serialization");
						} else {
								System.out.println("Different object After serialization");
						}
				} catch (Exception e ){
						e.printStackTrace();
				}
		}

		private static void cloningTest(FinalSingleton singleton){
				try {
						FinalSingleton newInstance = (FinalSingleton) singleton.clone();

						if(singleton == newInstance){
								System.out.println("Same object After cloning");
						} else {
								System.out.println("Different object After cloning");
						}
				} catch (Exception e) {
						System.out.println("Exception occurred while creating clone");
				}

		}
}
