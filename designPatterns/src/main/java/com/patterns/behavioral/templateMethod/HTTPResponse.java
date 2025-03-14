package com.patterns.behavioral.templateMethod;

public class HTTPResponse {
		private String type;

		private String body;

		private String header;

		public HTTPResponse(String type, String body, String header) {
				this.type = type;
				this.body = body;
				this.header = header;
		}

		public String getType() {
				return type;
		}

		public void setType(String type) {
				this.type = type;
		}

		public String getBody() {
				return body;
		}

		public void setBody(String body) {
				this.body = body;
		}

		public String getHeader() {
				return header;
		}

		public void setHeader(String header) {
				this.header = header;
		}
}
