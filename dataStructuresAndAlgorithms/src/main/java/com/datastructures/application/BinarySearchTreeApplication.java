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

				System.out.println("Inorder traversing : ");
				binarySearchTree.inOrderTraversing();

				System.out.println();
				System.out.println("Preorder traversing : ");
				binarySearchTree.preOrderTraversing();

				System.out.println();
				System.out.println("Postorder traversing : ");
				binarySearchTree.postOrderTraversing();

				System.out.println();
				System.out.println("Breath First search traversing : ");
				binarySearchTree.bFS();

				System.out.println();
				System.out.println("Breath First search traversing using queue : ");
				binarySearchTree.bFSUsingQueue();

				System.out.println();
				System.out.println("Height of Tree : "+ binarySearchTree.heightOfTree());

				System.out.println();
				System.out.println("Height of Node 6 : "+ binarySearchTree.heightOfNode(6));

				System.out.println();
				System.out.println("Height of Node 11 : "+ binarySearchTree.heightOfNode(11));

				System.out.println();
				System.out.println("Height of Node 46 : "+ binarySearchTree.heightOfNode(46));

				System.out.println();
				System.out.println("Depth of Node 11 : "+ binarySearchTree.depthOfNode(11));

				System.out.println();
				System.out.println("Depth of Node 6 : "+ binarySearchTree.depthOfNode(6));

				System.out.println();
				System.out.println("Depth of Node 89 : "+ binarySearchTree.depthOfNode(89));

				System.out.println();
				System.out.println("Is sub tree : "+ binarySearchTree.isSubtree(new Integer[]{6, 4, 9, 11}));
		}
}
