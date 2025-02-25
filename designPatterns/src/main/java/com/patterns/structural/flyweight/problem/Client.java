package com.patterns.structural.flyweight.problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Client interacting with trees.
 */
public class Client {
		public static void main(String[] args) {
				System.out.println("Without Flyweight example started");

				List<Tree> trees = new ArrayList<>();
				Tree mangoAtStart = new Tree("Mango", "Green", 0, 0);
				Tree mangoInMiddle = new Tree("Mango", "Green", 0, 5);
				Tree mangoAtEnd = new Tree("Mango", "Green", 0, 10);

				trees.add(mangoAtStart);
				trees.add(mangoInMiddle);
				trees.add(mangoAtEnd);


				Tree appleAtStart = new Tree("Apple", "Green", 5, 0);
				Tree appleInMiddle = new Tree("Apple", "Green", 5, 5);
				Tree appleAtEnd = new Tree("Apple", "Green", 5, 10);
				trees.add(appleAtStart);
				trees.add(appleInMiddle);
				trees.add(appleAtEnd);

				Tree mapleAtStart = new Tree("Maple", "Red", 10, 0);
				Tree mapleInMiddle = new Tree("Maple", "Red", 10, 5);
				Tree mapleAtEnd = new Tree("Maple", "Red", 10, 10);

				trees.add(mapleAtStart);
				trees.add(mapleInMiddle);
				trees.add(mapleAtEnd);

				trees.forEach(tree -> tree.display());

				System.out.println("Without Flyweight example started");
		}
}
