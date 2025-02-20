package com.patterns.structural.bridge.problem;

public class RedTriangleShape extends TriangleShape{
		@Override public void draw(){
				super.draw();
				System.out.println("Filling Red color");
		}

}
