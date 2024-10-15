package com.patterns.creational.builder;

/**
 * Steps to implement builder patter.
 * 1. Create nested class to build the outer class.
 * 2. We can provide static method to return object of inner build class so that anyone can use it.
 * 3. Provide setters to this inner build class.
 * 4. Provide build method to return final outer class object with provided properties.
 * 5. Create constructor which will take builder object and set all attributes.
 * 6. Make constructor private so that no one can use from outside.
 * 7. Expose only getter method from outer class.
 */
public class URLWithBuilder {

		public static Builder getBuilder(){
				return new Builder();
		}

		static class Builder{
				private String protocol;
				private String hostname;
				private String port;
				private String queryParam;
				private String pathParam;

				public Builder protocol(String protocol){
						this.protocol = protocol;
						return this;
				}

				public Builder hostname(String hostname){
						this.hostname = hostname;
						return this;
				}

				public Builder port(String port){
						this.port = port;
						return this;
				}

				public Builder pathParam(String pathParam){
						this.pathParam = pathParam;
						return this;
				}

				public Builder queryParam(String queryParam){
						this.queryParam = queryParam;
						return this;
				}

				public URLWithBuilder build(){
						return new URLWithBuilder(this);
				}

		}


		private final String protocol;
		private final String hostname;
		private final String port;
		private final String queryParam;
		private final String pathParam;

		private URLWithBuilder(Builder builder){
				this.protocol = builder.protocol;
				this.hostname = builder.hostname;
				this.port = builder.port;
				this.pathParam = builder.pathParam;
				this.queryParam = builder.queryParam;
		}

		public String getProtocol() {
				return protocol;
		}

		public String getHostname() {
				return hostname;
		}

		public String getPort() {
				return port;
		}

		public String getQueryParam() {
				return queryParam;
		}

		public String getPathParam() {
				return pathParam;
		}
}
