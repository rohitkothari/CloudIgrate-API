package cloudigrate.api.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import cloudigrate.api.controller.*;

public class GoogleStorageTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		/*
		 * Testing of Storage - uploadObject()
		 */
		StorageController sc = new StorageController();
		File object = new File("/Users/rohietkothari/Desktop/trial.txt");
		//System.out.println("Testing Google Storage - createBucket() ");
		//sc.createBucket("rohit");
		//sc.uploadObject("rohit", object);
		//sc.downloadObject("rohit", "trial.txt", "/Users/rohietkothari/Desktop");
		sc.deleteObject("rohit", "trial.txt");
	}

}
