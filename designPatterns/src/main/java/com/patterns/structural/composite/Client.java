package com.patterns.structural.composite;

public class Client {
		public static void main(String[] args) {
				System.out.println("Composite Design pattern client started");

				FileSystem file = getFile();

				file.getSize();

				System.out.println("Composite Design pattern client started");
		}


		private static FileSystem getFile(){
				Directory rootDirectory = new Directory("root");

				FileSystem rootFile = new File(1, "rootFile.txt");
				Directory levelOneDir = new Directory("level1");

				rootDirectory.addFile(rootFile);
				rootDirectory.addFile(levelOneDir);


				FileSystem level1File = new File(5, "level1-1.txt");
				FileSystem level1File1 = new File(3, "level1-2.txt");
				Directory level2 = new Directory("level2");


				levelOneDir.addFile(level1File);
				levelOneDir.addFile(level1File1);
				levelOneDir.addFile(level2);


				FileSystem level2File = new File(8, "level2.txt");
				level2.addFile(level2File);

				return rootDirectory;
		}
}
