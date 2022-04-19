package com.hpm.nichols;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ProjectIo {
	public static ArrayList<Patient> patientList = new ArrayList<Patient>();

	@SuppressWarnings("unchecked")
	public static ArrayList<Patient> loadPatients() {

		try {
			FileInputStream fileIn = new FileInputStream("patientInfoFolder/patients.ser");
			ObjectInputStream mapIn = new ObjectInputStream(new BufferedInputStream(fileIn));

			patientList.addAll((ArrayList<Patient>) mapIn.readObject());

			mapIn.close();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Patient i : patientList) {

			if (i.getSymptoms() == null) {
				System.out.println(i.getSymptoms());
				i.setSymptoms("");
			}

		}

		return patientList;
	}

	public static void savePatients(ArrayList<Patient> map, String firstName, String lastName, int roomNumber,
			int patientID, String insuranceInfo, String symptoms, boolean quarentined, int safetyLevel, String drNote) {
		long startTime = System.currentTimeMillis();
		
		patientList.addAll(map);
		Patient p = new Patient(firstName, lastName, roomNumber, patientID, insuranceInfo, symptoms, quarentined,
				safetyLevel);
		p.setDrNotes(drNote);
		patientList.add(p);

		try {

			FileOutputStream writeFile = new FileOutputStream("patientInfoFolder/patients.ser");
			
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(writeFile));

			out.writeObject(patientList);

			out.close();
			writeFile.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		long timeTaken = (endTime - startTime) / 1000;
		System.out.println("It took " + timeTaken + " Seconds to save");
	}

}
