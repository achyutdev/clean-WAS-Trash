package main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author achyutdev
 *
 */

public class CleanWASTrash {

	private static String ROOT_PATH = "";
	private static Map<String, String> profilePaths = new HashMap<>();

	public static void main(String... strings) {
		boolean status = false;
		try {
			if (isSavedPathAvailable()) {
				fetchPaths();
				displayAllPaths();
				if (isPathNew()) {
					getProfilePathFromUser();
				} else {
					selectOneFromProfilePaths();
				}
			} else {
				getProfilePathFromUser();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (isVerifyEnteredPath()) {
			System.out.println("-----------------------------Clean WAS started -------------------------------------");
			try {
				for (Directory directory : Directory.values()) {
					status = deleteDirectory(ROOT_PATH + directory.path());
				}
				if (status) {
					System.out.println(
							"----------------------------Clean WAS Completed -------------------------------------");
				}

			} catch (Exception e) {
				System.out.println("Error Occur: " + e.getMessage());
			}
		} else {
			System.out.println("You Entered wrong path !");
		}
	}

	private static boolean isPathNew() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Do you want to use new profile? [Y/N] ");
		if (scanner.nextLine().equalsIgnoreCase(Constants.YES.get())) {
			return true;
		}
		return false;
	}

	private static boolean isVerifyEnteredPath() {
		return ROOT_PATH.contains(Constants.PROFILE.get());
	}

	private static void displayAllPaths() {
		System.out.println("You have following profile saved:");
		System.out.println("-------------------------------------------------");
		for (String shortName : profilePaths.keySet()) {
			System.out.println("   " + getFormattedName(shortName) + " : " + profilePaths.get(shortName));
		}
		System.out.println("-------------------------------------------------");

	}

	private static String getFormattedName(String shortName) {
		if (shortName.length() < 10) {
			while (shortName.length() != 10) {
				shortName = shortName + " ";
			}
		}
		return shortName;
	}

	private static void selectOneFromProfilePaths() {
		Scanner sc = null;
		do {
			sc = new Scanner(System.in);
			System.out.println("Enter one of short name: ");
			String getShtName = sc.nextLine().toLowerCase();
			if (profilePaths.get(getShtName) != null) {
				ROOT_PATH = profilePaths.get(getShtName);
				break;
			} else {
				System.out.println("Can not found " + getShtName + "profile short name.");
			}

		} while (true);
		sc.close();
	}

	private static void getProfilePathFromUser() {
		System.out.println("Enter Root path of your profile - example : C:/home/achyutdev/myfolder.\nRoot Path: ");
		Scanner scanner = new Scanner(System.in);
		ROOT_PATH = scanner.nextLine();
		System.out.println("Do you want to save this profile? [Y/N] ");
		if (scanner.nextLine().equalsIgnoreCase(Constants.YES.get())) {
			System.out.println("Give short name - e.g. auto :");
			String shortName = scanner.nextLine();
			saveProfile(shortName);
		} else {
			System.out.println("Profile is not saved !");
		}
		scanner.close();

	}

	private static void saveProfile(String shortName) {

		File file = new File(Constants.FILE_NAME.get());
		BufferedWriter bw = null;

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(Constants.FILE_NAME.get(), true));
			bw.write(shortName.toLowerCase() + " " + ROOT_PATH);
			bw.newLine();
			bw.flush();

		} catch (IOException e) {
			System.out.println("System Error !");
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					System.out.println("Something bad just happen !");
				}
		}

	}

	private static boolean isSavedPathAvailable() {
		File pathSavedFile = new File(Constants.FILE_NAME.get());
		return pathSavedFile.exists();
	}

	/**
	 * @throws IOException
	 *             fetch saved profile path from file
	 */
	private static void fetchPaths() throws IOException {
		File file = new File(Constants.FILE_NAME.get());
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			String[] arr = line.split(" ");
			profilePaths.put(arr[0].toLowerCase(), arr[1].toLowerCase());
		}
		fileReader.close();
	}

	/**
	 * method only delete file and folder inside path directory
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static boolean deleteDirectory(String path) throws Exception {
		File rootFile = new File(path);
		if (rootFile.exists()) {
			File[] subFiles = rootFile.listFiles();
			for (File file : subFiles) {
				startDeleting(new File(file.getPath()));
			}
		} else {
			System.out.println(rootFile.getPath() + " - path does not exist !");
		}
		return true;
	}

	/**
	 * Method delete everything inside file directory
	 * 
	 * @param directory
	 * @return
	 * @throws Exception
	 */
	public static boolean startDeleting(File directory) throws Exception {
		if (directory.isDirectory()) {
			File[] subDir = directory.listFiles();
			for (int i = 0; i < subDir.length; i++) {
				System.out.println(i);
				boolean isCompleted = startDeleting(subDir[i]);
				if (!isCompleted) {
					return false;
				}
			}
		}
		System.out.println("Deleting : \t\t" + directory.getName());
		return directory.delete();
	}
}