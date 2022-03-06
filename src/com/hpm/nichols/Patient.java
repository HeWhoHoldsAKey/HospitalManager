package com.hpm.nichols;

public class Patient {
	private String firstName;
	private String lastName;
	private int roomNumber;
	private int patientID;
	private String insuranceInfo;
	private String symptoms;
	private boolean quarentined;
	private int safetyLevel;

	public Patient(String firstName, String lastName, int roomNumber, int patientID, String insuranceInfo,
			String symptoms, boolean quarentined, int safetyLevel) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.roomNumber = roomNumber;
		this.patientID = patientID;
		this.insuranceInfo = insuranceInfo;
		this.symptoms = symptoms;
		this.quarentined = quarentined;
		this.safetyLevel = safetyLevel;

	}

	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
	}

	public String getLastName() {
		// TODO Auto-generated method stub
		return lastName;
	}

	public int getRoomNumber() {
		// TODO Auto-generated method stub
		return roomNumber;
	}

	public int getPatientId() {
		// TODO Auto-generated method stub
		return patientID;
	}

	public String getInsuranceInfo() {
		// TODO Auto-generated method stub
		return insuranceInfo;
	}

	public String getSymptoms() {
		// TODO Auto-generated method stub
		return symptoms;
	}

	public boolean getQuarentined() {
		// TODO Auto-generated method stub
		return quarentined;
	}

	public int getSafetyLevel() {
		// TODO Auto-generated method stub
		return safetyLevel;
	}

}
