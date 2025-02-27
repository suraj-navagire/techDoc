package com.patterns.behavioral.chainOfResponsibility;

public class AuthRequest {

		private String userName;

		private String password;

		private String otp;

		public AuthRequest(String userName, String password, String otp) {
				this.userName = userName;
				this.password = password;
				this.otp = otp;
		}

		public String getUserName() {
				return userName;
		}

		public void setUserName(String userName) {
				this.userName = userName;
		}

		public String getPassword() {
				return password;
		}

		public void setPassword(String password) {
				this.password = password;
		}

		public String getOtp() {
				return otp;
		}

		public void setOtp(String otp) {
				this.otp = otp;
		}
}
