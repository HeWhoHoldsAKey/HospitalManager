package com.hpm.nichols;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ProjectIo {
	public static ArrayList<Patient> patientList = new ArrayList<Patient>();

	// Stores the file info and stuff here so you can change it and such.
	static File patients = new File("patientInfoFolder/patients.ser");

	@SuppressWarnings("unchecked")
	public static ArrayList<Patient> loadPatients() {
		// I wanted to see how long it takes to load the data.
		long startTime = System.currentTimeMillis();

		// Try ti bring in the file that holds the patient info.
		try {
			FileInputStream fileIn = new FileInputStream(patients);
			ObjectInputStream mapIn = new ObjectInputStream(new BufferedInputStream(fileIn));

			// Makes sure the list is clear before adding the refreshed or new objects
			patientList.clear();
			patientList.addAll((ArrayList<Patient>) mapIn.readObject());

			// This is called map in because i originally used a hash map instead of an
			// array list but using the list i saved about 5x the loading time.
			// This also needs to happen because memory leaks are bad.
			mapIn.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// There is sometimes an issue with the symptoms being null and i honestly don't
		// know why but i may have fixed it. So this could possibly be removed
		for (Patient i : patientList) {

			if (i.getSymptoms() == null) {
				System.out.println(i.getSymptoms());
				i.setSymptoms("");
			}

		}

		// Lets you know how long it takes to load the patient list
		long endTime = System.currentTimeMillis();
		long timeTaken = (endTime - startTime) / 1000;
		System.out.println("It took " + timeTaken + " Seconds to load");

		// Returns a list.
		return patientList;
	}

	public static void savePatients(ArrayList<Patient> list) {
		// I wanted to see how long it takes to store the data.
		long startTime = System.currentTimeMillis();

		// Again clears the list to prevent "stacking" as i call it.
		patientList.clear();
		patientList.addAll(list);
		System.out.println(patientList.size());

		try {

			FileOutputStream writeFile = new FileOutputStream(patients);

			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(writeFile));

			out.writeObject(patientList);

			out.close();
			writeFile.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Lets you know how long it takes to save the patient list
		long endTime = System.currentTimeMillis();
		long timeTaken = (endTime - startTime) / 1000;
		System.out.println("It took " + timeTaken + " Seconds to save");
	}

}
