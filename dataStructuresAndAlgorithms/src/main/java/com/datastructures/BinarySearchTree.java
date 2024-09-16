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

		public void inOrderTraversing(){
				Node currentNode = root;

				if(currentNode == null){
						return;
				}

				inOrderTraversing(currentNode);

		}

		private void inOrderTraversing(Node node) {

				if(node == null){
						return;
				}

				inOrderTraversing(node.left);
				System.out.print(node.data+",");
				inOrderTraversing(node.right);
		}

		public void preOrderTraversing(){

				Node currentNode = root;

				if(currentNode == null){
						return;
				}

				preOrderTraversing(currentNode);
		}

		private void preOrderTraversing(Node node){
				if(node == null){
						return;
				}

				System.out.print(node.data+",");
				preOrderTraversing(node.left);
				preOrderTraversing(node.right);
		}

		public void postOrderTraversing(){

				Node currentNode = root;

				if(currentNode == null){
						return;
				}

				postOrderTraversing(currentNode);
		}

		private void postOrderTraversing(Node node){
				if(node == null){
						return;
				}

				postOrderTraversing(node.left);
				postOrderTraversing(node.right);
				System.out.print(node.data+",");
		}

		public int heightOfTree(){
				return heightOfNode(root) -1 ;

		}

		/**
		 * This will return number of vertices from given node to leaf node.
		 *
		 * @param node
		 * @return
		 */
		private int depth(Node node){
				if(node == null){
						return 0;
				}

				int leftHeight = depth(node.left);
				int rightHeight = depth(node.right);

				int max = 0;

				if(leftHeight > rightHeight){
						return leftHeight + 1;
				} else {
						return rightHeight +1;
				}
		}

		/**
		 * Edges between given node to leaf node.
		 *
		 * @param node
		 * @return
		 */
		public int heightOfNode(Node node){
				return depth(node) - 1;
		}


		/**
		 * Here node is not provided. Directly value is provided. SO we need to find respective node. As we know its binary search tree
		 * means value must be sorted left right correctly. So we can compare and try to find respective node. Then we can pass that node
		 * and try to find height.
		 *
		 * @param node
		 * @return
		 */
		public int heightOfNode(T node){

				if(node == null){
						return 0;
				}

				Node currentNode = root;

				int height = 0;
				while (currentNode!=null){
						if(currentNode.data.equals(node)){
								height = heightOfNode(currentNode);
						}

						if(currentNode.data.compareTo(node) <0){
								currentNode = currentNode.right;
						} else {
								currentNode = currentNode.left;
						}
				}

				return height;
		}
}
