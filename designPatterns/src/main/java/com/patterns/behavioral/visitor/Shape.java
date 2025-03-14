package com.patterns.behavioral.visitor;

/**
 * This is Element.
 */
public interface Shape {
		void accept(ShapeVisitor visitor);
}
