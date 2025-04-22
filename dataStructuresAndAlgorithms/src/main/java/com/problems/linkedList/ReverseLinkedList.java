package com.problems.linkedList;

/**
 * This problem is to reverse linked list without any extra data structure.
 */
public class ReverseLinkedList {
		public static void main(String[] args) {
				LinkedList linkedList = new LinkedList();
				linkedList.add(1);
				linkedList.add(3);
				linkedList.add(5);
				linkedList.add(8);

				linkedList.printAll();
				linkedList.reverse();
				linkedList.printAll();

		}
}


class LinkedList{
		Node head;

		class Node{
				Integer data;
				Node next;
		}

		void add(Integer data){
				Node currentNode = head;
				Node n = new Node();
				n.data = data;
				if(currentNode == null){
						head = n;
						return;
				}

				while(currentNode.next!=null){
						currentNode = currentNode.next;
				}

				currentNode.next = n;
		}

		void reverse(){
				Node node = reverseNode(head);
				node.next = null;
		}

		private Node reverseNode(Node currentNode){
				if(currentNode.next ==null){
						head = currentNode;
						return currentNode;
				}

				Node node = reverseNode(currentNode.next);
				node.next = currentNode;
				return currentNode;
		}

		void printAll(){
				Node currentNode = head;
				while (currentNode!=null){
						System.out.print(currentNode.data+" ");
						currentNode = currentNode.next;
				}
				System.out.println();
		}


}