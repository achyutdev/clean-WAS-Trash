package main.java.constant;

public enum MatchDirectory {

	APPLICATIONS("config/cells/NW*Node*Cell/applications/niinternet-*-ear.ear"),
	BLAS("config/cells/NW*Node*Cell/blas/niinternet-*-ear"),
	CUS("config/cells/NW*Node*Cell/cus/niinternet-*-ear");

	private final String path;

	MatchDirectory(String path) {
		this.path = path;
	}
	
	public String path(){
		return path;
	}
	
}
