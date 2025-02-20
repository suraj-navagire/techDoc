package com.patterns.structural.bridge.problem;

public class RedSquareShape extends SquareShape{
		@Override public void draw(){
				super.draw();
				System.out.println("Filling Red color");
		}
}
