package com.patterns.solid;

public class LiskovSubstitutionShapeViolation {
		public static void main(String[] args) {
				//Client is working on Rectangle reference.
				Rectangle rectangle = new Rectangle();
				rectangle.setHeight(10);
				rectangle.setWidth(5);

				//Here client is calling area
				System.out.println(rectangle.area());


				//Replacing object of superclass with sublcass.
				rectangle = new Square();

				rectangle.setWidth(5);
				//Here there is a separate method to set height which is not correct. Even if the client passes the same value for width and height,
				// conceptually this design is wrong because width and height are not independent for Square.  Also square setter methods will not allow width and height
				//to remain independent so that is also wrong.
				rectangle.setHeight(5);

				System.out.println(rectangle.area());

		}

}

class Rectangle{
		int width, height;

		public void setWidth(int width) {
				this.width = width;
		}

		public void setHeight(int height) {
				this.height = height;
		}

		public int area(){
				return width*height;
		}
}

class Square extends Rectangle{

		@Override public void setWidth(int width) {
				this.width = width;
				this.height = width;
		}

		@Override public void setHeight(int height) {
				this.width = height;
				this.height = height;
		}
}