package com.patterns.behavioral.visitor;

/**
 * Concrete Element
 */
public class Square implements Shape{

		private int leftSide;

		private int rightSide;

		private int topSide;

		private int bottomSide;

		public Square(int leftSide, int rightSide, int topSide, int bottomSide) {
				this.leftSide = leftSide;
				this.rightSide = rightSide;
				this.topSide = topSide;
				this.bottomSide = bottomSide;
		}

		@Override public void accept(ShapeVisitor visitor) {
				visitor.visit(this);
		}

		public int getLeftSide() {
				return leftSide;
		}

		public int getRightSide() {
				return rightSide;
		}

		public int getTopSide() {
				return topSide;
		}

		public int getBottomSide() {
				return bottomSide;
		}

}
