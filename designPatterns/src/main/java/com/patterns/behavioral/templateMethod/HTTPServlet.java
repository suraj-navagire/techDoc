package com.patterns.behavioral.templateMethod;

/**
 * Template method class.
 *
 * This class contains service method. This is a template method. It defines skeleton of algorithm.
 *
 * Subclass can override specific implementation i.e. doGet and doPost method.
 *
 * It follows open-closed principle.
 *
 */
public abstract class HTTPServlet {

		public final void service(HTTPRequest request, HTTPResponse response){
				if("GET".equals(request.getType())){
						doGet(request, response);
				} else if("POST".equals(request.getType())){
						doPost(request, response);
				} else {
						throw new UnsupportedOperationException("Operation not supported");
				}
		}

		public void doGet(HTTPRequest request, HTTPResponse response){
				throw new UnsupportedOperationException("GET operation not supported by current application.");
		}

		public void doPost(HTTPRequest request, HTTPResponse response){
				throw new UnsupportedOperationException("POST operation not supported my current application.");
		}

}
