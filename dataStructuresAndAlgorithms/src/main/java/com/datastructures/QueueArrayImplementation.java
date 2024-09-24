package com.datastructures;

public class QueueArrayImplementation<T> {

		private int head;

		private int tail;

		private Object[] queue;

		private int max;

		public QueueArrayImplementation(int max) {
				this.max = max;
				this.queue = new Object[max];
				this.head = -1;
				this.tail = 0;
		}

		private QueueArrayImplementation(){
		}

		public void enqueue(T element){
				if(isFull()){
						throw new RuntimeException("Cannot insert new element as queue is full");
				}

				System.out.println("Adding element : "+ element);
				if(head == -1){
						head = 0;
				}

				queue[tail] = element;

				tail++;

		}

		public T dequeue(){
				if(isEmpty()){
						throw new RuntimeException("Cannot return element as queue is empty");
				}

				T element = (T) queue[head];

				for(int i=0 ; i<tail-1; i++){
						queue[i] = queue[i+1];
				}

				if(tail == 1){
						head = -1;
				}

				tail--;

				return element;
		}

		public T peek(){
				if(isEmpty()){
						throw new RuntimeException("Cannot return element as queue is empty");
				}

				return (T) queue[head];
		}

		public boolean isEmpty(){
				return head == -1;
		}

		public boolean isFull(){
				return tail == max;
		}


}
