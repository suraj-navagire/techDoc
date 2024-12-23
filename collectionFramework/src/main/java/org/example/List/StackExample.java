package org.example.List;

import java.util.Stack;

public class StackExample {
		public static void main(String[] args) {
				System.out.println("StackExample Started");

				Stack<Integer> stack = new Stack<>();
				stack.push(10);
				stack.push(30);
				stack.push(50);

				System.out.println(stack.search(50));
				System.out.println(stack.search(30));
				System.out.println(stack.search(10));

		}
}
