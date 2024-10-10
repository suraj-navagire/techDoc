package com.patterns.solid;

public class SingleResponsibilityCorrect {
		public static void main(String[] args) {

				Alert1 alert1 = new SMS();
				alert1.sendAlert();

				Alert1 alert2 = new Mail();
				alert2.sendAlert();
		}
}


interface Alert1{
		void sendAlert();
}

class SMS implements Alert1{

		@Override public void sendAlert() {
				System.out.println("Sending sms");
		}
}

class Mail implements Alert1{

		@Override public void sendAlert() {
				System.out.println("Sending Mail");
		}
}