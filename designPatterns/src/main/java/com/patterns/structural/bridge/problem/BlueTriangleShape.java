package com.patterns.structural.bridge.problem;

public class BlueTriangleShape extends TriangleShape {
		@Override public void draw(){
				super.draw();
				System.out.println("Filling Blue color");
		}
}
