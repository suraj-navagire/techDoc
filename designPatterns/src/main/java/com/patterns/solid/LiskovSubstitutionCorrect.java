package com.patterns.solid;

public class LiskovSubstitutionCorrect {

		public static void main(String[] args) {
				IColor color = new Blue1();
				color.getColor();
				//output: Blue
		}
}

interface IColor{
		public void getColor();
}

class Green1 implements IColor {
		public void getColor() {
				System.out.println("Green");
		}
}

class Blue1 implements IColor {
		public void getColor() {
				System.out.println("Blue");
		}
}
