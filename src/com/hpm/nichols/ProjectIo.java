package com.hpm.nichols;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ProjectIo {
	public static HashMap<Integer, Patient> patientMap = new HashMap<Integer, Patient>();

	@SuppressWarnings("unchecked")
	public static Map<Integer, Patient> loadPatients() {

		try {
			FileInputStream fileIn = new FileInputStream("patientInfoFolder/patients.ser");
			ObjectInputStream mapIn = new ObjectInputStream(fileIn);

			patientMap.putAll((Map<? extends Integer, ? extends Patient>) mapIn.readObject());

			mapIn.close();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Patient i : patientMap.values()) {

			if (i.getSymptoms() == null) {
				System.out.println(i.getSymptoms());
				i.setSymptoms("");
			}

		}

		return patientMap;
	}

	public static void savePatients(HashMap<Integer, Patient> map, int hashKey, String firstName, String lastName,
			int roomNumber, int patientID, String insuranceInfo, String symptoms, boolean quarentined, int safetyLevel,
			String drNote) {
		patientMap.putAll(map);
		Patient p = new Patient(firstName, lastName, roomNumber, patientID, insuranceInfo, symptoms, quarentined,
				safetyLevel);
		p.setDrNotes(drNote);
		patientMap.put(hashKey, p);

		try {

			FileOutputStream writeFile = new FileOutputStream("patientInfoFolder/patients.ser");

			ObjectOutputStream out = new ObjectOutputStream(writeFile);

			out.writeObject(patientMap);

			out.close();
			writeFile.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
