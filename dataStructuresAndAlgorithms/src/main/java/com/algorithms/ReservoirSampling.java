package com.algorithms;

import java.util.*;

public class ReservoirSampling {
		public static void main(String[] args) {
				System.out.println("ReservoirSampling started");

				try {
						ReservoirSampling application = new ReservoirSampling();

						int n = 50;

						List<Integer> list = application.createListOfRandomNumbers(n);

						int selectedRandomNumber = application.selectRandomNumber(list);

						int k = 10;

						int[] selectedKNumbers = application.selectRandomKNumbers(list, k);

						System.out.println("Selected random number : " + selectedRandomNumber);

						System.out.println("Selected random k numbers : ");
						for (int i = 0; i < k; i++) {
								System.out.print(selectedKNumbers[i] + ",");
						}

				} catch (Exception e) {
						e.printStackTrace();
				}

				System.out.println();
				System.out.println("ReservoirSampling ended");
		}

		private int selectRandomNumber(List<Integer> list) {
				int randomNumber = -1;

				Iterator<Integer> listIterator = list.iterator();
				int i = 0;
				Random rand = new Random();
				while (listIterator.hasNext()) {
						++i;
						int random = rand.nextInt(i);

						int num = listIterator.next();
						if (random == (i-1)) {
								randomNumber = num;
						}
				}

				return randomNumber;
		}

		private int[] selectRandomKNumbers(List<Integer> list, int k) {
				int[] randomKNumber = new int[k];

				Iterator<Integer> listIterator = list.iterator();
				int i = 0;
				for (; i < k && listIterator.hasNext(); i++) {
						randomKNumber[i] = listIterator.next();
				}

				Random rand = new Random();
				while (listIterator.hasNext()) {
						int random = rand.nextInt(i);

						int num = listIterator.next();

						if (random < k) {
								randomKNumber[random] = num;
						}
						i++;
				}

				return randomKNumber;
		}

		private List<Integer> createListOfRandomNumbers(int n) {
				List<Integer> list = new LinkedList<>();
				Random rand = new Random();
				for (int i = 0; i < n; i++) {
						list.add(rand.nextInt(i+1));
				}

				return list;
		}
}



