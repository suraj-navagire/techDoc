package com.patterns.behavioral.chainOfResponsibility;

public class Client {
		public static void main(String[] args) {
				System.out.println("Chain of responsibility started");

				AuthHandler userNameHandler = new UserNameValidator();
				AuthHandler passwordHandler = new PasswordValidator();
				AuthHandler otpHandler = new OTPValidator();

				userNameHandler.setNextHandler(passwordHandler);
				passwordHandler.setNextHandler(otpHandler);

				boolean isValid = userNameHandler.handle(new AuthRequest("Max", "Max@123", "0000"));

				System.out.println("Is valid request : "+isValid);
				System.out.println("Chain of responsibility started");
		}
}
