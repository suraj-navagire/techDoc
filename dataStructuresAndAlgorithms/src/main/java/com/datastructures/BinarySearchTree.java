package com.datastructures;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> {

		private Node root;

		public class Node {
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
				if(currentNode.data.compareTo(data) < 0) {
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

				if(currentNode.data.compareTo(data) < 0){
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

		/**
		 * It returns number of edges from root node to farthest leaf node.
		 *
		 * @return
		 */
		public int heightOfTree(){
				return heightOfNode(root);

		}

		/**
		 * This will return number of vertices from given node to leaf node.
		 *
		 * @param node
		 * @return
		 */
		private int height(Node node){
				if(node == null){
						return 0;
				}

				int leftHeight = -1;
				if(node.left != null){
						leftHeight = height(node.left);
				}

				int rightHeight = -1;
				if(node.right != null) {
						rightHeight = height(node.right);
				}


				if(leftHeight == -1 && rightHeight == -1){
						return 0;
				}

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
		private int heightOfNode(Node node){
				return height(node);
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

				boolean isNodePresent = false;

				while (currentNode!=null){
						if(currentNode.data.equals(node)){
								height = heightOfNode(currentNode);
								isNodePresent = true;
						}

						if(currentNode.data.compareTo(node) <0){
								currentNode = currentNode.right;
						} else {
								currentNode = currentNode.left;
						}
				}

				return isNodePresent ? height : -1;
		}

		/**
		 * Returns number of edges between given node from root node.
		 *
		 * Need to minus 1 from given number to find number of edges
		 *
		 * @param node
		 * @return
		 */
		public int depthOfNode(T node){
				int height = depthOfNode(root, node);
				return height != -1 ? height: -1;
		}

		/**
		 * Returns number of vertices from root node to given node
		 *
		 * @param currentNode
		 * @param node
		 * @return
		 */
		private int depthOfNode(Node currentNode, T node){

				if(currentNode == null) {
						return  -1;
				}

				if(currentNode.data.compareTo(node) == 0){
						return 0;
				}

				int leftDepth = depthOfNode(currentNode.left, node);
				int rightDepth = depthOfNode(currentNode.right, node);

				if(leftDepth != -1 || rightDepth != -1){
						if(leftDepth != -1){
								return leftDepth +1;
						} else {
								return rightDepth + 1;
						}
				} else {
						return -1;
				}

		}


		public boolean isSubtree(Node subtree){
				if(subtree == null){
						return  false;
				}

				return isSubtree(root, subtree);
		}

		private boolean isSubtree(Node tree, Node subtree){
				if(tree == null) {
						return false;
				}

				boolean isSubtree = false;
				if(tree.data.equals(subtree.data)){
						isSubtree = isIdentical(tree, subtree);
				} else{
						boolean isLeftSubtree = isSubtree(tree.left, subtree);
						boolean isRightSubtree = isSubtree(tree.right, subtree);

						isSubtree = isLeftSubtree || isRightSubtree;
				}

				return isSubtree;
		}

		/**
		 * Check if both tree are same
		 *
		 * @param tree
		 * @param subtree
		 * @return
		 */
		private boolean isIdentical(Node tree , Node subtree){

				//If both node are null means same
				if(tree == null && subtree == null){
						return true;
				}

				//If subtree node is not null but main tree node is null means it is not sub tree
				if(tree == null && subtree!=null){
						return false;
				}

				//If main tree node is not null but subtree node is null then its fine. It can be sub tree
				if(tree != null && subtree == null){
						return true;
				}


				boolean isLeftSubtreeIdentical = false;
				boolean isRightSubtreeIdentical = false;

				if(tree.data.equals(subtree.data)){
						isLeftSubtreeIdentical = isIdentical(tree.left, subtree.left);
						isRightSubtreeIdentical = isIdentical(tree.right, subtree.right);
				} else{
						return false;
				}

				return isLeftSubtreeIdentical && isRightSubtreeIdentical;
		}


		public boolean isSubtree(T[] data){
				Node node =new Node();

				node.data = data[0];

				node.left = new Node();
				node.right = new Node();

				node.left.data = data[1];
				node.right.data = data[2];

				node.right.right = new Node();
				node.right.right.data = data[3];

				return isSubtree(node);
		}

		public void bFS() {
				if(root == null){
						System.out.println("Tree is empty");
				}

				int heightOfTree = heightOfTree();

				//Here we are passing which level of rows we need to print. Adding +1 in for loop condition since height is number of edegs
				//but we need to traverse vertices. If we don't add +1 then we might miss last row
				for(int i=1; i<=heightOfTree+1; i++){
						bFS(root, i);
				}
		}

		private void bFS(Node node, int i) {
				if(i==1){
						//This indicates we have reached desired level
						System.out.print(node.data+",");
						return;
				}

				if(node.left != null){
						//Here we are decrementing i for making it 1 till level we want
						bFS(node.left, i-1);
				}

				if(node.right != null){
						bFS(node.right, i-1);
				}
		}

		public void bFSUsingQueue(){
				if(root == null){
						return;
				}

				Queue<Node> queue = new LinkedList<>();

				queue.add(root);

				while (!queue.isEmpty()){
						Node currentNode = queue.poll();

						System.out.print(currentNode.data+",");

						Node leftNode = currentNode.left;
						Node rightNode = currentNode.right;

						if(leftNode != null){
								queue.add(leftNode);
						}

						if(rightNode != null){
								queue.add(rightNode);
						}
				}
		}
}
