package com.datastructures;

public class BinarySearchTree<T extends Comparable<T>> {

		private Node root;

		private class Node {
				T data;
				Node left;
				Node right;
		}


		public void add(T data){
				if(root == null) {
						root = new Node();
						root.data = data;
						System.out.println("Root :" +data);
						return;
				}

				insertData(root, data);
		}

		private void insertData(Node currentNode, T data){
				if(currentNode.data.compareTo(data) <= 0) {
						if(currentNode.right == null) {
								Node right = new Node();
								right.data = data;

								currentNode.right = right;
								System.out.println("right :" +data);
						} else {
								insertData(currentNode.right, data);
						}
				} else {
						if(currentNode.left == null) {
								Node left = new Node();
								left.data = data;
								currentNode.left = left;
								System.out.println("left :" +data);
						} else {
								insertData(currentNode.left, data);
						}
				}
		}

		public boolean search(T data){
				return searchNode(root, data);
		}

		private boolean searchNode(Node currentNode , T data){

				if(currentNode == null){
						return false;
				}

				if(data.equals(currentNode.data)){
						return true;
				}

				if(currentNode.data.compareTo(data) <= 0){
						return searchNode(currentNode.right, data);
				} else {
						return searchNode(currentNode.left, data);
				}
		}

}
