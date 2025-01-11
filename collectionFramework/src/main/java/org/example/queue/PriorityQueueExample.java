package org.example.queue;

import java.util.PriorityQueue;

public class PriorityQueueExample {
		public static void main(String[] args) {
				System.out.println("PriorityQueueExample Started");

				PriorityQueue<String> priorityQueue = new PriorityQueue<>();
				priorityQueue.add("b");
				priorityQueue.add("t");
				priorityQueue.add("a");
				priorityQueue.add("z");

				while (!priorityQueue.isEmpty()){
						System.out.println("priorityQueue : "+ priorityQueue.poll());
				}

				PriorityQueue<String> priorityQueueReverseOrder = new PriorityQueue<>( (a, b) -> -(a.compareTo(b)));
				priorityQueueReverseOrder.add("b");
				priorityQueueReverseOrder.add("t");
				priorityQueueReverseOrder.add("a");
				priorityQueueReverseOrder.add("z");

				while (!priorityQueueReverseOrder.isEmpty()){
						System.out.println("priorityQueueReverseOrder : "+ priorityQueueReverseOrder.poll());
				}

		}
}
