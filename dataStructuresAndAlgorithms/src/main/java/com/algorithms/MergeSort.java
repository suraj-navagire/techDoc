package com.algorithms;

public class MergeSort {

		public static void main(String[] args) {
				System.out.println("MergeSort Started");

				int[] a = new int[] {3,12, 15, 9,2, 17};
				divide(a, 0, 5);

				System.out.println("Sorted array : ");
				for (int n : a){
						System.out.print(n+" ");
				}

		}
		private static void divide(int[] a, int si, int ei){
				if(si >= ei){
						return;
				}

				int mid = si + (ei-si)/2;

				divide(a, si, mid);
				divide(a, mid+1, ei);

				conquer(a, si, mid, ei);
		}

		private static void conquer(int[] a, int si, int mid, int ei){
				int[] merged = new int[ei - si +1];

				int idx1 = si;
				int idx2 = mid+1;
				int x = 0;
				while (idx1 <= mid &&  idx2 <= ei){
						if(a[idx1] <= a[idx2]){
								merged[x] = a[idx1];
								idx1++;
						} else {
								merged[x] = a[idx2];
								idx2++;
						}

						x++;
				}

				while (idx1 <= mid){
						merged[x] = a[idx1];
						x++;
						idx1++;
				}

				while (idx2 <= ei){
						merged[x] = a[idx2];
						x++;
						idx2++;
				}

				x=0;
				for (int i=si;i<=ei;i++){
						a[i] = merged[x];
						x++;
				}

		}
}
