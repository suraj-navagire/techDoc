package com.patterns.behavioral.chainOfResponsibility;

public abstract class AuthHandler {

		protected AuthHandler nextHandler;

		public void setNextHandler(AuthHandler handler){
				this.nextHandler = handler;
		}

		public abstract boolean handle(AuthRequest request);
}
