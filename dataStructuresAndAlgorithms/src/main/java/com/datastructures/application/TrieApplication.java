package com.datastructures.application;

import com.datastructures.Trie;

public class TrieApplication {
		public static void main(String[] args) {
				System.out.println("TrieApplication started");
				String[] words = new String[]{"i", "like", "sam", "samsung", "mobile", "ice"};

				Trie trie = new Trie();

				for (String word : words){
						trie.insert(word);
				}

				System.out.println("Data inserted");

				boolean isExists = trie.search("mobile");

				System.out.println("Is mobile exists in trie ? : " + isExists);

				isExists = trie.search("sam");

				System.out.println("Is sam exists in trie ? : " + isExists);

				isExists = trie.search("john");

				System.out.println("Is john exists in trie ? : " + isExists);


				isExists = trie.wordBreak("ilike");

				System.out.println("Is following string \"ilikesamsung\" made up of words present in words array : " + isExists);

				isExists = trie.wordBreak("ilikesamsun");
				System.out.println("Is following string \"ilikesamsun\" made up of words present in words array : " + isExists);

				isExists = trie.searchPrefix("mo");

				System.out.println("Is there any word starting with mo :  " + isExists);

				isExists = trie.searchPrefix("san");

				System.out.println("Is there any word starting with san :  " + isExists);

		}
}
