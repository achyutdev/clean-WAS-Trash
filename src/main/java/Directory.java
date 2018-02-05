package main.java;

public enum Directory {
	
	DIRECORTY1("/directory1"),
	DIRECORTY2("/directory2/sub1"),
	DIRECORTY3("/directory3/jsp");

	private final String path;

	Directory(String path) {
		this.path = path;
	}
	
	public String path(){
		return path;
	}
}
