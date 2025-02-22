package com.patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a composite. It contains one or more components.
 */
public class Directory implements FileSystem{
		private String name;

		private List<FileSystem> files;

		public Directory(String name) {
				this.name = name;
				files = new ArrayList<>();
		}

		public void addFile(FileSystem file){
				this.files.add(file);
		}

		@Override public int getSize() {
				int totalSize = 0;
				for(FileSystem file : files){
						totalSize = totalSize + file.getSize();
				}

				System.out.println("Size of dir "+name+" : "+ totalSize);
				return totalSize;
		}
}
