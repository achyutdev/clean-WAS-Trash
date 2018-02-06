package main.java.utils;

public class SystemInfo {
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	public static boolean isWindow(){
		return (OS.indexOf("win") >= 0);
	}
	
	public static boolean isLinux(){
		return (OS.indexOf("linux") >= 0);
	}
	
	public static boolean isMac(){
		return (OS.indexOf("mac") >= 0);
	}

	public static String get(){
		return OS;
	}
}
