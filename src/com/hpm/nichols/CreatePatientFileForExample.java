package com.hpm.nichols;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CreatePatientFileForExample {

	public static void main(String[] args) {
		System.out.println("Creating Example File");
		ArrayList<Patient> patientMap = new ArrayList<Patient>();

		try {
			File firstNames = new File("randFolder/firstNames.txt");
			File lastNames = new File("randFolder/lastNames.txt");
			File medicalSymptoms = new File("randFolder/medicalSymptoms.txt");

			RandomAccessFile fnRead = new RandomAccessFile(firstNames, "r");
			RandomAccessFile lnRead = new RandomAccessFile(lastNames, "r");
			RandomAccessFile msRead = new RandomAccessFile(medicalSymptoms, "r");

			for (int i = 0; i < 1000; i++) {

				long randomLocation = (long) (Math.random() * fnRead.length());
				fnRead.seek(randomLocation);
				fnRead.readLine();
				String fn = fnRead.readLine();

				randomLocation = (long) (Math.random() * lnRead.length());
				lnRead.seek(randomLocation);
				lnRead.readLine();
				String ln = fnRead.readLine();

				randomLocation = (long) (Math.random() * msRead.length());
				msRead.seek(randomLocation);
				msRead.readLine();
				String ms = msRead.readLine();

				if (ms == null) {
					do {
						randomLocation = (long) (Math.random() * msRead.length());
						msRead.seek(randomLocation);
						msRead.readLine();
						ms = msRead.readLine();
					} while (ms == null);
				}

				int randA = ThreadLocalRandom.current().nextInt(0, 5000 + 1);
				int randB = ThreadLocalRandom.current().nextInt(0, 999999999 + 1);
				int randC = ThreadLocalRandom.current().nextInt(0, 5 + 1);

				Boolean qua;
				if (ThreadLocalRandom.current().nextInt(0, 1 + 1) == 1) {

					qua = true;

				} else {
					qua = false;
				}

				Patient p = new Patient(fn, ln, randA, randB, "None", ms, qua, randC);
				patientMap.add(p);

			}

			fnRead.close();
			lnRead.close();
			msRead.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (Patient i : patientMap) {
			System.out.println(i.getSymptoms());
		}

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
