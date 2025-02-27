package com.patterns.behavioral.chainOfResponsibility;

public class OTPValidator extends AuthHandler{
		@Override public boolean handle(AuthRequest request) {
				if (!"0000".equals(request.getOtp())){
						System.out.println("Invalid OTP");
						return false;
				}

				return nextHandler != null ? nextHandler.handle(request) : true;
		}
}
