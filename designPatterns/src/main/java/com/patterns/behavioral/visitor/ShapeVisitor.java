package com.patterns.behavioral.visitor;


/**
 * Visitor interface.
 */
public interface ShapeVisitor {
		void visit(Triangle triangle);

		void visit(Square square);
}
