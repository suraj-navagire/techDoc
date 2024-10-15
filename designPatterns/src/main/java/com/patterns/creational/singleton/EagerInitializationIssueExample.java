package com.patterns.creational.singleton;

public class EagerInitializationIssueExample {
		public static void main(String[] args) {
				//Here as you can see we are using getClassNameOFThis method. We don.t want object of EagerInitialization
				//Still object gets created.
				String className = EagerInitialization.getClassNameOFThis();
				System.out.println(className);
		}
}
