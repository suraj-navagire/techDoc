package com.datastructures;

public class Trie {

		private Node root;

		static class Node {
				Node[] children;
				boolean eow;

				Node(){
						this.children = new Node[26];
				}
		}

		public Trie(){
				root = new Node();
		}

		public void insert(String word){
				Node currentNode = root;

				for(int i=0; i<word.length(); i++){
						int index = word.charAt(i) - 'a';

						Node child = currentNode.children[index];

						if(child == null){
								child = new Node();
								currentNode.children[index] = child;
						}

						if(i == word.length()-1){
								child.eow = true;
						}
						currentNode = child;
				}
		}

		public boolean search(String word){

				Node currentNode = root;

				boolean isExists = false;

				for (int i=0;i<word.length();i++){
						int index = word.charAt(i) - 'a';

						Node child = currentNode.children[index];

						if(child == null){
								return false;
						}

						if(i == word.length()-1){
								isExists = child.eow;
						}

						currentNode = child;
				}

				return isExists;
		}

		public boolean wordBreak(String word){
				if(word.length() == 0){
						return true;
				}


				for (int i=1;i<=word.length();i++){
						String str1 = word.substring(0,i);
						String str2 = word.substring(i,word.length());

						if(search(str1) && wordBreak(str2)){
								return true;
						}
				}

				return false;
		}



		public boolean searchPrefix(String prefix){
				Node currentNode = root;

				for (int i=0;i<prefix.length();i++){
						int index = prefix.charAt(i) - 'a';

						if(currentNode.children[index] == null){
								return false;
						}

						currentNode = currentNode.children[index];
				}

				return true;
		}

}
