package cloudigrate.api.controller;

import java.io.File;

import cloudigrate.api.controller.*;

public class StorageTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * Testing of Storage - uploadObject()
		 */
		StorageController sc = new StorageController();
		File object = new File("/Users/rohietkothari/Desktop/trial.txt");
		System.out.println("Starting upload of file-"+object.getName());
		sc.uploadObject("cirk29", object);
		
	}

}
