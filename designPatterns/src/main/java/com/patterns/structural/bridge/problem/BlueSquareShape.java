package com.patterns.structural.bridge.problem;

public class BlueSquareShape extends SquareShape{
		@Override public void draw(){
				super.draw();
				System.out.println("Filling Blue color");
		}
}
