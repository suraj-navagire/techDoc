package com.patterns.behavioral.templateMethod;

import java.lang.reflect.InvocationTargetException;

public class Application {

		private static HTTPServlet servlet;

		public static void main(String[] args) {
				System.out.println("Template method design pattern started");

				//Client registering its servlet with application using some xml. Application will register that servlet using following mechanism.
				String clientServletNameProvidedInXML = "com.patterns.behavioral.templateMethod.MySubclassServlet";
				try {
						servlet = (HTTPServlet) Class.forName(clientServletNameProvidedInXML).getDeclaredConstructor().newInstance();
				} catch (ClassNotFoundException | NoSuchMethodException e) {
						throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
						throw new RuntimeException(e);
				} catch (InstantiationException e) {
						throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
				}

				//New request comes. Now application will use template method to process this request. But required doGet and doPost
				// implementation is given by subclasses only i.e. by client who is using this servlet framework.
				servlet.service(new HTTPRequest("GET", "My first request", "My custom header"), new HTTPResponse(null, null, null));

				System.out.println("Template method design pattern started");
		}

}
