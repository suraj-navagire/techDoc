package com.datastructures.application;

import com.datastructures.BinaryTreeArrayImplementation;

import java.util.Random;

public class BinaryTreeArrayImplementationExample<T> {
		public static void main(String[] args) {
				System.out.println("BinaryTreeArrayImplementationExample Started");

				BinaryTreeArrayImplementation binaryTreeArrayImplementation = new BinaryTreeArrayImplementation(12);

				Random rand = new Random();
				for(int i=0;i<12;i++){
						binaryTreeArrayImplementation.add(rand.nextInt(100));
				}

				binaryTreeArrayImplementation.printTree();

				System.out.println("BinaryTreeArrayImplementationExample ended");

		}
}
