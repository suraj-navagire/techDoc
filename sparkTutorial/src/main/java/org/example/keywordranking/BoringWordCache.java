package org.example.keywordranking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is contains cache which contains list of words which is not required in keyword
 * ranking.
 */
public class BoringWordCache {
		private static Set<String> boringWordCache;

		static {
				System.out.println("BoringWordCache started");
				boringWordCache = new HashSet<>();
				//Following files can be downloaded from spark udemy tutorial attachment
				String file ="src/main/resources/boringwords.txt";

				try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
						String currentLine = null;
						while ((currentLine = reader.readLine()) != null) {
								boringWordCache.add(currentLine);
						}
				} catch (FileNotFoundException e) {
						throw new RuntimeException(e);
				} catch (IOException e) {
						throw new RuntimeException(e);
				}

				System.out.println("BoringWordCache completed");
		}

		public static boolean isWordExists(String word){
				return boringWordCache.contains(word) || word.isEmpty() || word.equals(" ");
		}
}