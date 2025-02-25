package com.patterns.structural.flyweight.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Here client is interacting with Tree object and using Flyweight factory to reuse flyweight object.
 *
 * Here we have used tree type object without changing functionality.
 */
public class Client {
		public static void main(String[] args) {
				System.out.println("Flyweight example started");

				List<Tree> trees = new ArrayList<>();

				Tree mangoAtStart = new Tree(TreeTypeFactory.getTreeType("Mango", "Green"), 0, 0);
				Tree mangoInMiddle = new Tree(TreeTypeFactory.getTreeType("Mango", "Green"), 0, 5);
				Tree mangoAtEnd = new Tree(TreeTypeFactory.getTreeType("Mango", "Green"), 0, 10);

				trees.add(mangoAtStart);
				trees.add(mangoInMiddle);
				trees.add(mangoAtEnd);

				Tree appleAtStart = new Tree(TreeTypeFactory.getTreeType("Apple", "Green"), 5, 0);
				Tree appleInMiddle = new Tree(TreeTypeFactory.getTreeType("Apple", "Green"), 5, 5);
				Tree appleAtEnd = new Tree(TreeTypeFactory.getTreeType("Apple", "Green"), 5, 10);

				trees.add(appleAtStart);
				trees.add(appleInMiddle);
				trees.add(appleAtEnd);

				Tree mapleAtStart = new Tree(TreeTypeFactory.getTreeType("Maple", "Red"), 10, 0);
				Tree mapleInMiddle = new Tree(TreeTypeFactory.getTreeType("Maple", "Red"), 10, 5);
				Tree mapleAtEnd = new Tree(TreeTypeFactory.getTreeType("Maple", "Red"), 10, 10);

				trees.add(mapleAtStart);
				trees.add(mapleInMiddle);
				trees.add(mapleAtEnd);

				trees.forEach(tree -> tree.display());

				System.out.println("Without Flyweight example started");
		}
}
