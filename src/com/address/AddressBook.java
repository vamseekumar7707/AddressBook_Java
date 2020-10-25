package com.address;
import java.io.*;
import java.util.Scanner;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
public class AddressBook {

	public static void main(String[] args) {

		// DISPLAYING ALL ADDRESS BOOKS PRESENT
		System.out.println("\n------------Text files------------");
		File directoryPath = new File(
				"C:\\Users\\vamse\\eclipse-workspace\\AddressBook\\src\\com\\address");
		File[] files = directoryPath.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});

		for (File file : files) {

			String fileName = file.getName();
			fileName = fileName.replace(".txt", "");
			System.out.println(fileName);
		}

		Scanner sc = new Scanner(System.in);
		String addressBookName = null;
		int end1 = 0;
		System.out.println("WELCOME TO ADDRESS BOOK CODE");
		int choice1 = 0;
		while (end1 == 0) {
			System.out.println("Select from the options below");
			System.out.println("1.\tOPEN Address Book");
			System.out.println("2.\tCREATE An Address Book");
			System.out.println("3.\tDELETE An Address Book");
			System.out.println("4.\tDELETE All Address Books");
			System.out.println("5.\tEXIT");
			choice1 = sc.nextInt();
			switch (choice1) {
			case 1:
				System.out.println("Enter Address Book Name You want to open");
				addressBookName = sc.next();
				sc.nextLine();
				openAddressBook(addressBookName);
				break;
			case 2:
				System.out.println("Enter Address Book Name you want to create");
				addressBookName = sc.next();
				createAddresssBook(addressBookName);
				break;

			case 3:
				System.out.println("Enter Address Book Name you want to DELETE");
				addressBookName = sc.next();
				deleteAddresssBook(addressBookName);
				break;
			case 4:
				System.out.println("do you really want to delete all");
				System.out.println("1.yes 2.No");
				int choice2 = sc.nextInt();
				if (choice2 == 1) {
					deleteAllAddressBook();
				}
				break;
			case 5:
				end1 = 1;
			}
		}

	}

	private static void deleteAllAddressBook() {

		File directoryPath = new File(
				"C:\\Users\\vamse\\eclipse-workspace\\AddressBook\\src\\com\\address");
		File[] files = directoryPath.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});

		for (File file : files) {

			String fileName = file.getName();
			File file1 = new File(
					"C:\\Users\\vamse\\eclipse-workspace\\AddressBook\\src\\com\\address"
							+ fileName);
			if (file1.delete()) {
				System.out.println("File deleted successfully");
			} else {
				System.out.println("Failed to delete the file");
			}
		}
	}

	private static void deleteAddresssBook(String addressBookName) {
		String fileName = addressBookName + ".txt";
		File file = new File(
				"C:\\Users\\vamse\\eclipse-workspace\\AddressBook\\src\\com\\address"
						+ fileName);
		if (file.delete()) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("Failed to delete the file");
		}
	}

	private static void createAddresssBook(String addressBookName) {
		String fileName = addressBookName + ".txt";
		System.out.println(fileName);
		System.out.println("create address book");
		try {
			File myObj = new File(
					"C:\\Users\\vamse\\eclipse-workspace\\AddressBook\\src\\com\\address"
							+ fileName);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
				System.out.println(myObj);
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		/* WRITING COLUMNS IN FIRST LINE OF THE FILE */
		try {
			FileWriter myWriter = new FileWriter(
					"C:\\Users\\vamse\\eclipse-workspace\\AddressBook\\src\\com\\address"
							+ fileName);
			myWriter.write("First Name\tLast Name\tEmail\tAddress\tCity\tState\tZip\tPhone Number\t");
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static void openAddressBook(String addressBookName) {
		String phoneNumber = null;
		String fileName = addressBookName + ".txt";
		BufferedReader r;
		try {
			r = new BufferedReader(new FileReader(
					"C:\\Users\\vamse\\eclipse-workspace\\AddressBook\\src\\com\\address"
							+ fileName));
			String s = "", line = null;
			while ((line = r.readLine()) != null) {
//				s += line;
				System.out.println("\t\t" + line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("\t\tEnter your choice");
		System.out.println("1.\t\tAdd a record");
		System.out.println("2.\t\tdelete a record");
		System.out.println("3.\t\tsearch a record");
		System.out.println("4.\t\tupdate a record");
		System.out.println("5.\t\tEXIT");
		Scanner scan = new Scanner(System.in);
		int choice2 = scan.nextInt();
		switch (choice2) {
		case 1:
			System.out.println("\tEnter your phone Number");
			phoneNumber = scan.next();
			addARecord(addressBookName, phoneNumber);
			break;
		case 2:
			System.out.println("\tEnter your phone Number");
			phoneNumber = scan.next();
			deleteARecord(addressBookName, phoneNumber);
			break;
		case 3:
			System.out.println("\tEnter your phone Number");
			phoneNumber = scan.next();
			searchARecord(addressBookName, phoneNumber);
			break;
		case 4:
			updateARecord(addressBookName);
		}

	}

	private static void updateARecord(String addressBookName) {

	}

	private static void searchARecord(String addressBookName, String input) {

		String fileName = addressBookName + ".txt";
		File file = new File(
				"C:\\Users\\vamse\\eclipse-workspace\\AddressBook\\src\\com\\address"
						+ fileName);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				int index = line.indexOf(input);
				if (index != -1) {
					System.out.println(line);
				}
			}
		} catch (Exception e) {
			System.out.println("Error occured while processing the file");
			e.printStackTrace();
		}

	}

	public static void deleteARecord(String addressBookName, String phoneNumber) {

		String fileName = addressBookName + ".txt";
		String dest = "Output.txt";
		File file = new File(
				"C:\\Users\\vamse\\eclipse-workspace\\AddressBook\\src\\com\\address"
						+ fileName);
		
		
		try {
			FileWriter myWriter = new FileWriter(
					"C:\\Users\\vamse\\eclipse-workspace\\AddressBook\\src\\com\\address"
							+ dest,true);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				int index = line.indexOf(phoneNumber);
				
				if (index != -1) {
					
				} else {
					System.out.println(line);
					myWriter.write("\n"+line);
				}
			}
			myWriter.close();

		} catch (Exception e) {
			System.out.println("Error occured while processing the file");
			e.printStackTrace();
		}

	}

	public static void addARecord(String addressBookName, String phoneNumber) {
		String fileName = addressBookName + ".txt";
		Scanner scan = new Scanner(System.in);
		System.out.println("\n\tEnter your First Name");
		String fName = scan.next();
		System.out.println("\n\tEnter your Last Name");
		String lName = scan.next();
		System.out.println("\nEnter your Email Id");
		String emailId = scan.next();
		System.out.println("\n\tEnter your Address");
		String Address = scan.next();
		System.out.println("\n\tEnter your city");
		String city = scan.next();
		System.out.println("\n\tEnter your State");
		String state = scan.next();
		System.out.println("\n\tEnter your Zip");
		int zip = scan.nextInt();

		try {
			FileWriter myWriter = new FileWriter(
					"C:\\Users\\vamse\\eclipse-workspace\\AddressBook\\src\\com\\address"
							+ fileName,true);
			myWriter.write("\n" + fName + "\t" + lName + "\t" + emailId + "\t" + Address + "\t" + city + "\t" + state
					+ "\t" + zip + "\t" + phoneNumber + "\t");
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}
}