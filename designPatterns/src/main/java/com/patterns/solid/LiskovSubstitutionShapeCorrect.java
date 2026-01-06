package com.patterns.solid;

public class LiskovSubstitutionShapeCorrect {
		public static void main(String[] args) {

				//Here shape has area method. Both class implements that and gives correct result. Conceptually and technically this is correct way.
				Shape rectangle1 = new Rectangle1(5, 10);
				rectangle1.area();

				rectangle1 = new Square1(5);
				rectangle1.area();
		}
}

interface Shape{
		int area();
}

class Rectangle1 implements Shape {
		int width;
		int height;

		public Rectangle1(int width, int height) {
				this.width = width;
				this.height = height;
		}

		public int area() {
				return width * height;
		}

		public void setWidth(int width) {
				this.width = width;
		}

		public void setHeight(int height) {
				this.height = height;
		}
}

class Square1 implements Shape {
		int side;

		public Square1(int side) {
				this.side = side;
		}

		public int area() {
				return side * side;
		}

}