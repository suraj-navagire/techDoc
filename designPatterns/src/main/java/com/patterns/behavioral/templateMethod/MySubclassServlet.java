package com.patterns.behavioral.templateMethod;

public class MySubclassServlet extends HTTPServlet{

		@Override public void doGet(HTTPRequest request, HTTPResponse response) {
				System.out.println("Handling GET request : "+request);
		}

		@Override public void doPost(HTTPRequest request, HTTPResponse response) {
				System.out.println("Handling POST request : "+request);
		}
}
