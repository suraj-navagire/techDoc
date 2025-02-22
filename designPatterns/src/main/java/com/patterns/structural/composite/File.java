package com.patterns.structural.composite;

/**
 * This is a leaf object as it doesn't contain any children
 *
 */
public class File implements FileSystem {

		private int size;

		private String name;

		public File(int size, String name) {
				this.size = size;
				this.name = name;
		}

		@Override public int getSize() {
				System.out.println("File Size of file "+name+" : "+size);
				return size;
		}
}
