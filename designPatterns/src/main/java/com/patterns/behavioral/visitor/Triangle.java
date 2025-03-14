package com.patterns.behavioral.visitor;

/**
 * Concrete Element.
 */
public class Triangle implements Shape{

		private int leftSide;

		private int rightSide;

		private int bottom;

		public Triangle(int leftSide, int rightSide, int bottom) {
				this.leftSide = leftSide;
				this.rightSide = rightSide;
				this.bottom = bottom;
		}

		@Override public void accept(ShapeVisitor visitor) {
				visitor.visit(this);
		}

		public int getLeftSide() {
				return leftSide;
		}

		public void setLeftSide(int leftSide) {
				this.leftSide = leftSide;
		}

		public int getRightSide() {
				return rightSide;
		}

		public void setRightSide(int rightSide) {
				this.rightSide = rightSide;
		}

		public int getBottom() {
				return bottom;
		}

		public void setBottom(int bottom) {
				this.bottom = bottom;
		}
}
