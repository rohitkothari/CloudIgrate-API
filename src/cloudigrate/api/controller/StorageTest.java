package cloudigrate.api.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class StorageTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		StorageController sc = new StorageController();
		
		int choice=0;
		
		Scanner userIn = new Scanner(System.in);
		System.out.println("test");
		System.out.println("Storage Tests- Select one choice of the following:");
		System.out.println("1. Test uploadObject()");
		System.out.println("2. Test downloadObject()");
		choice = userIn.nextInt();
		switch(choice) {
		
		/*
		 * Testing of Storage - uploadObject()
		 */
		case 1: 
			System.out.println("Testing - uploadObject()");
			
			File object = new File("/Users/rohietkothari/Desktop/trial.txt");
			System.out.println("Starting upload of file-"+object.getName());
//			sc.uploadObject("cirk29", object);
			
			break;

		/*
		 * Testing of Storage - downloadObject()
		 */
		case 2: 
			System.out.println("Testing - downloadObject()");
			
			System.out.println("Starting download of file");
			File file = null;
			//File file = sc.downloadObject("cirk28", "trial.txt", "/Users/rohietkothari");
			System.out.println("Filename: "+file.getName());
			System.out.println("File location: "+file.getCanonicalPath());
			
			break;

		default:
			System.out.println("Sorry, you entered a wrong test choice. Please try again.");
			userIn.close();
			break;
		}
		
	}

}
