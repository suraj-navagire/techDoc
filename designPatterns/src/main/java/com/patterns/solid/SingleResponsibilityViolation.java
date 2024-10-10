package com.patterns.solid;

public class SingleResponsibilityViolation {
		public static void main(String[] args) {
				Alert alert = new Alert();

				//Here Alert class is doing all the work. This is violation. Task should get divided in separate class.
				//For sms we should have different class and for email as well.
				alert.sendAlertOnMail();
				alert.sendAlertOnSMS();

		}
}

//Alert class has mechanism to send alert on sms and mail. This is violation.
class Alert {
		void sendAlertOnSMS(){
				System.out.println("Sending alert on sms");

		}

		void sendAlertOnMail(){
				System.out.println("Sending alert on mail");
		}
}
