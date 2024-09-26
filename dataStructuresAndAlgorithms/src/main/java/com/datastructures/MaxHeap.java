package com.datastructures;

public class MaxHeap<T extends Comparable> {

		private Object[] heap;

		private int currentIndex;

		public MaxHeap(int heapSize){
				this.heap = new Object[heapSize];
				this.currentIndex = 0;
		}

		public void heapify(T[] inputTree){
				int size = inputTree.length;
				for(int i=size-1;i>=0;i--){
						compareChildrenAndAdjust(i, inputTree);
				}
		}

		public T extractMax(){
				if(isEmpty()){
						throw new RuntimeException("Cannot extract element as heap is empty");
				}

				T resultElement = (T) heap[0];

				heap[0] = heap[currentIndex-1];
				heap[currentIndex-1] = null;

				compareChildrenAndAdjust(0, heap);

				currentIndex--;

				return resultElement;

		}


		private void compareChildrenAndAdjust(int index, Object[] inputHeap){
				T element = (T) inputHeap[index];

				int leftChildIndex = getLeftChildIndex(index);
				int rightChildIndex = getRightChildIndex(index);
				T leftChild = null;
				if(leftChildIndex < inputHeap.length){
						leftChild = (T) inputHeap[leftChildIndex];
				}

				T rightChild = null;
				if(rightChildIndex < inputHeap.length){
						rightChild = (T) inputHeap[rightChildIndex];
				}

				if(leftChild == null && rightChild == null){
						return;
				}

				if(leftChild != null && rightChild == null){
						if(leftChild.compareTo(element) > 0){
								swap(index, leftChildIndex, inputHeap);
								index = leftChildIndex;
								compareChildrenAndAdjust(index, inputHeap);
						}
						return;
				}

				if(leftChild == null && rightChild != null){
						if(rightChild.compareTo(element) > 0){
								swap(index, rightChildIndex, inputHeap);
								index = rightChildIndex;
								compareChildrenAndAdjust(index, inputHeap);
						}
						return;
				}


				if(leftChild.compareTo(rightChild) > 0){
						if(leftChild.compareTo(element) > 0){
								swap(index, leftChildIndex, inputHeap);
								index = leftChildIndex;
								compareChildrenAndAdjust(index, inputHeap);
						}
				} else {
						if(rightChild.compareTo(element) > 0){
								swap(index, rightChildIndex, inputHeap);
								index = rightChildIndex;
								compareChildrenAndAdjust(index, inputHeap);
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
						swap(parentIndex, index, heap);
						index = parentIndex;

						compareParentAndAdjustHeap(index);
				}
		}

		private void swap(int index1, int index2, Object[] inputHeap){
				T temp = (T) inputHeap[index1];
				inputHeap[index1] = inputHeap[index2];
				inputHeap[index2] = temp;
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
