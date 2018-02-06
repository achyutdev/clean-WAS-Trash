package main.java.constant;

public enum Directory {
	
	DIRECORTY1("/wstemp"),
	DIRECORTY2("/workspace"),
	DIRECORTY3("/temp"),
	DIRECORTY4("/config/temp"),
	DIRECORTY5("/tranlog");

	private final String path;

	Directory(String path) {
		this.path = path;
	}
	
	public String path(){
		return path;
	}
}
