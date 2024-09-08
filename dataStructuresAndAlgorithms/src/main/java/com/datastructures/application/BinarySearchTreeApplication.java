package com.datastructures.application;

import com.datastructures.BinarySearchTree;

public class BinarySearchTreeApplication {
		public static void main(String[] args) {
				BinarySearchTreeApplication application = new BinarySearchTreeApplication();
				application.start();
		}

		private void start(){
				System.out.println("Binary Search tree application started");

				BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
				binarySearchTree.add(3);
				binarySearchTree.add(6);
				binarySearchTree.add(4);
				binarySearchTree.add(2);
				binarySearchTree.add(9);
				binarySearchTree.add(11);
				binarySearchTree.add(8);

				System.out.println("Is 6 present in tree : "+ binarySearchTree.search(6));
				System.out.println("Is 7 present in tree : "+ binarySearchTree.search(7));

				System.out.println("Is 9 present in tree : "+ binarySearchTree.search(9));
				System.out.println("Is 11 present in tree : "+ binarySearchTree.search(11));

				System.out.println("Is 1 present in tree : "+ binarySearchTree.search(1));


				System.out.println("Binary search tree application ended");
		}
}
