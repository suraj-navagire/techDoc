package com.algorithms;

public class HeapSort<T extends Comparable> {
		public void sort(T[] input){

				//Step 1. Heapify
				heapify(input);

				// One by one sort element

				int currentInputSize = input.length - 1;
				while(currentInputSize!=0){
						//Step 2. Extract root element and adjust heap property.
						T extractedElement = extractElement(input, currentInputSize);

						//Step 3. Place this extracted element at currentLastIndex
						input[currentInputSize] = extractedElement;
						currentInputSize--;
				}

		}

		private T extractElement(T[] input, int sizeBoundry) {
				T extractedElement = input[0];

				input[0] = input[sizeBoundry];

				input[sizeBoundry] = null;

				compareChildrenAndAdjust(0, input, sizeBoundry);

				return extractedElement;
		}

		private void heapify(T[] input){
				int size = input.length;

				for(int i=size-1;i>=0;i--){
						compareChildrenAndAdjust(i, input, input.length);
				}
		}

		private void compareChildrenAndAdjust(int index, T[] input, int sizeBoundry){
				T element = input[index];

				int leftChildIndex = getLeftChildIndex(index);
				int rightChildIndex = getRightChildIndex(index);

				T leftChild = null;
				T rightChild = null;
				if(leftChildIndex < sizeBoundry){
						leftChild = input[leftChildIndex];
				}

				if(rightChildIndex < sizeBoundry){
						rightChild = input[rightChildIndex];
				}


				if(leftChild == null && rightChild == null){
						return;
				}

				if(leftChild != null && rightChild == null) {
						if(element.compareTo(leftChild) < 0){
								swap(index, leftChildIndex, input);
								index = leftChildIndex;
								//This recursion is needed to check all children.
								compareChildrenAndAdjust(index, input, sizeBoundry);
						}
						return;
				}

				if(leftChild == null && rightChild != null){
						if(element.compareTo(rightChild) < 0){
								swap(index, rightChildIndex, input);
								index = rightChildIndex;
								compareChildrenAndAdjust(index, input, sizeBoundry);
						}
						return;
				}

				if(leftChild.compareTo(rightChild) < 0){
						if(element.compareTo(rightChild) < 0){
								swap(index, rightChildIndex, input);
								index = rightChildIndex;
								compareChildrenAndAdjust(index, input, sizeBoundry);
						}
				} else {
						if(element.compareTo(leftChild) < 0){
								swap(index, leftChildIndex, input);
								index = leftChildIndex;
								compareChildrenAndAdjust(index, input, sizeBoundry);
						}
				}
		}

		private void swap(int index1, int index2, T[] input){
				T temp = input[index1];
				input[index1] = input[index2];
				input[index2] = temp;
		}

		private int getLeftChildIndex(int index){
				return (2 * index) + 1;
		}

		private int getRightChildIndex(int index){
				return (2 * index) + 2;
		}

}
