package com.patterns.behavioral.visitor;

/**
 * Concrete visitor. This class is used to draw all the shapes.
 */
public class DrawShape implements ShapeVisitor{
		@Override public void visit(Triangle triangle) {
				System.out.println(String.format("Drawing triangle with left side : %s, right side : %s, bottom : %s", triangle.getLeftSide(), triangle.getRightSide(), triangle.getBottom()));
		}

		@Override public void visit(Square square) {
				System.out.println(String.format("Drawing square with left side : %s, right side : %s, top side : %s, bottom side : %s", square.getLeftSide(), square.getRightSide(), square.getTopSide(), square.getBottomSide()));

		}
}
