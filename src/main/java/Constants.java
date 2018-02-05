package main.java;

public enum Constants {

	FILE_NAME("directorypath"),
	YES("Y"),
	PROFILE("documents/delete");

	private final String name;

	Constants(String name) {
		this.name = name;
	}
	
	public String get(){
		return name;
	}
}
