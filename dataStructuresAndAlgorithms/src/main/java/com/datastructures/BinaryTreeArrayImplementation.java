package com.datastructures;

public class BinaryTreeArrayImplementation<T> {


		private Object[] binaryTree;

		private int currentIndex;

		public BinaryTreeArrayImplementation(int treeSize){
				binaryTree = new Object[treeSize];
				currentIndex = 0;
		}

		private BinaryTreeArrayImplementation(){
		}

		public void add(T element){
				if(element == null){
						return;
				}

				if(currentIndex == binaryTree.length){
						throw new RuntimeException("Tree is full");
				}

				binaryTree[currentIndex] = element;
				currentIndex++;
		}

		public void add(T parent, T child){
				int parentIndex = 0;
				for(int i =0; i<binaryTree.length;i++){
					if(binaryTree[i].equals(parent)){
							parentIndex = i;
					}
				}

				int leftChildIndex = 2*parentIndex + 1;
				int rightChildIndex = 2*parentIndex + 1;

				if(binaryTree[leftChildIndex] == null){
						binaryTree[leftChildIndex] = child;
				} else {
						binaryTree[rightChildIndex] = child;
				}
		}

		public void printTree(){
				for(int i = 0; i<(binaryTree.length/2); i++){
						int leftChildTree = 2*i + 1;
						int rightChildTree = 2*i + 2;
						if(rightChildTree >= binaryTree.length){
								System.out.println("Parent : "+binaryTree[i]+" -> Left child : "+binaryTree[leftChildTree]);
						} else {
								System.out.println("Parent : "+binaryTree[i]+" -> Left child : "+binaryTree[leftChildTree]+" Right child : "+binaryTree[rightChildTree]);
						}

				}
		}
}
