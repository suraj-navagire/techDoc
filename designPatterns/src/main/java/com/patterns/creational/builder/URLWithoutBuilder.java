package com.patterns.creational.builder;

/**
 * In this example we are creating URL. Basic URL understanding is it should have protocol and hostname.
 *  Other attributes like port , query param, path param are optional.
 *  Once URL is formed it should not get updated.
 *
 * 	1. Number of constructor arguments are more.
 * 	2. Not all constructor arguments are mandatory. Hence need to create all combinations of constructors.
 * 		Here we have created 4 combinations of constructors.
 * 	3. Even after creating URL object still we will be able to change this class state using setter methods.
 *
 * 	To solve these issue use builder pattern
 */
public class URLWithoutBuilder {
		private String protocol;
		private String hostname;
		private String port;
		private String queryParam;
		private String pathParam;

		public URLWithoutBuilder(String protocol, String hostname) {
				this.protocol = protocol;
				this.hostname = hostname;
		}

		public URLWithoutBuilder(String protocol, String hostname, String port) {
				this.protocol = protocol;
				this.hostname = hostname;
				this.port = port;
		}

		public URLWithoutBuilder(String protocol, String hostname, String port, String queryParam) {
				this.protocol = protocol;
				this.hostname = hostname;
				this.port = port;
				this.queryParam = queryParam;
		}

		public URLWithoutBuilder(String protocol, String hostname, String port, String queryParam, String pathParam) {
				this.protocol = protocol;
				this.hostname = hostname;
				this.port = port;
				this.queryParam = queryParam;
				this.pathParam = pathParam;
		}

		public String getProtocol() {
				return protocol;
		}

		public void setProtocol(String protocol) {
				this.protocol = protocol;
		}

		public String getHostname() {
				return hostname;
		}

		public void setHostname(String hostname) {
				this.hostname = hostname;
		}

		public String getPort() {
				return port;
		}

		public void setPort(String port) {
				this.port = port;
		}

		public String getQueryParam() {
				return queryParam;
		}

		public void setQueryParam(String queryParam) {
				this.queryParam = queryParam;
		}

		public String getPathParam() {
				return pathParam;
		}

		public void setPathParam(String pathParam) {
				this.pathParam = pathParam;
		}

}
