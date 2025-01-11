package org.example.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class BlockingQueueExample {
		public static void main(String[] args) {
				System.out.println("BlockingQueueExample Started");

				BlockingQueue<String> priorityBlockingQueue = new PriorityBlockingQueue<>(2);
				priorityBlockingQueue.add("b");
				priorityBlockingQueue.add("t");
				priorityBlockingQueue.add("a");
				priorityBlockingQueue.add("z");

				System.out.println("priorityBlockingQueue created with size 2 still we able to add 4 as it is unbounded.");
				while (!priorityBlockingQueue.isEmpty()){
						System.out.println("priorityBlockingQueue : "+ priorityBlockingQueue.poll());
				}

				BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(2);
				try{
						linkedBlockingQueue.add("b");
						linkedBlockingQueue.add("t");
						linkedBlockingQueue.add("a");
						linkedBlockingQueue.add("z");
				} catch (Exception e){
						System.out.println("Not able to add elements to linkedBlockingQueue more than capacity as it is bounded");
				}

				while (!linkedBlockingQueue.isEmpty()){
						System.out.println("linkedBlockingQueue : "+ linkedBlockingQueue.poll());
				}
		}
}
