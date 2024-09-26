package com.datastructures;

public class MaxHeap<T extends Comparable> {

		private Object[] heap;

		private int currentIndex;

		public MaxHeap(int heapSize){
				this.heap = new Object[heapSize];
				this.currentIndex = 0;
		}

		public T extractMax(){
				if(isEmpty()){
						throw new RuntimeException("Cannot extract element as heap is empty");
				}

				T resultElement = (T) heap[0];

				heap[0] = heap[currentIndex-1];
				heap[currentIndex-1] = null;

				compareChildrenAndAdjust(0);

				currentIndex--;

				return resultElement;

		}


		private void compareChildrenAndAdjust(int index){
				T element = (T) heap[index];

				int leftChildIndex = getLeftChildIndex(index);
				int rightChildIndex = getRightChildIndex(index);
				T leftChild = null;
				if(leftChildIndex < heap.length){
						leftChild = (T) heap[leftChildIndex];
				}

				T rightChild = null;
				if(rightChildIndex < heap.length){
						rightChild = (T) heap[rightChildIndex];
				}

				if(leftChild == null && rightChild == null){
						return;
				}

				if(leftChild != null && rightChild == null){
						if(leftChild.compareTo(element) > 0){
								swap(index, leftChildIndex);
								index = leftChildIndex;
								compareChildrenAndAdjust(index);
						}
						return;
				}

				if(leftChild == null && rightChild != null){
						if(rightChild.compareTo(element) > 0){
								swap(index, rightChildIndex);
								index = rightChildIndex;
								compareChildrenAndAdjust(index);
						}
						return;
				}


				if(leftChild.compareTo(rightChild) > 0){
						if(leftChild.compareTo(element) > 0){
								swap(index, leftChildIndex);
								index = leftChildIndex;
								compareChildrenAndAdjust(index);
						}
				} else {
						if(rightChild.compareTo(element) > 0){
								swap(index, rightChildIndex);
								index = rightChildIndex;
								compareChildrenAndAdjust(index);
						}
				}
		}

		public void insert(T element){
				if(element == null){
						return;
				}

				if(isFull()){
						throw new RuntimeException("Cannot insert new element as heap is full");
				}

				heap[currentIndex] = element;

				compareParentAndAdjustHeap(currentIndex);

				currentIndex++;

		}

		private void compareParentAndAdjustHeap(int index){
				T element = (T) heap[index];

				int parentIndex = getParentIndex(index);

				T parentElement = (T) heap[parentIndex];

				if(parentElement.compareTo(element) < 0){
						swap(parentIndex, index);
						index = parentIndex;

						compareParentAndAdjustHeap(index);
				}
		}

		private void swap(int index1, int index2){
				T temp = (T) heap[index1];
				heap[index1] = heap[index2];
				heap[index2] = temp;
		}

		private int getParentIndex(int childIndex){
				return ((childIndex -1)/2);
		}

		private int getLeftChildIndex(int parentIndex){
				return (2 * parentIndex) + 1;
		}

		private int getRightChildIndex(int parentIndex){
				return (2 * parentIndex) + 2;
		}

		public boolean isEmpty(){
				return currentIndex == 0;
		}

		public boolean isFull(){
				return currentIndex == heap.length;
		}
}
