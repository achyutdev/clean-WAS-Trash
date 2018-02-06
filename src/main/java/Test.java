package main.java;

import org.apache.tools.ant.DirectoryScanner;

public class Test {
	private static String OS = System.getProperty("os.name").toLowerCase();

	public static void main(String[] args) {
		System.out.println(OS);
	}
	
	private String[] findDirectory(){
		DirectoryScanner scanner = new DirectoryScanner();
		scanner.setIncludes(new String[]{"config/cells/NW*Node*Cell/applications/niinternet-*-ear*"});
		scanner.setBasedir(ROOT_PATH);
		scanner.setCaseSensitive(false);
		scanner.scan();
		String[] files = scanner.getIncludedFiles();
		return files;
	}

}
