package com.patterns.behavioral.templateMethod;

public class HTTPRequest {
		private String type;

		private String body;

		private String header;

		public HTTPRequest(String type, String msg, String header) {
				this.type = type;
				this.body = msg;
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

		@Override public String toString() {
				return "HTTPRequest{" + "type='" + type + '\'' + ", body='" + body + '\'' + ", header='" + header + '\''
						+ '}';
		}
}
