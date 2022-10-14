package com.hpm.nichols;

public class Patient implements java.io.Serializable {

	// This is a patient object which is serialized so that way an object can be
	// stored in a file.

	// This line is generated when the project is ran by java

	private static final long serialVersionUID = -4620745942752279887L;

	// This is the basic patient info that is used

	private String firstName;
	private String lastName;
	private int roomNumber;
	private int patientID;
	private String insuranceInfo;
	private String symptoms;
	private boolean quarentined;
	private int safetyLevel;
	private String drNotes;

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
		this.drNotes = "n/a";

	}

	// There is a way to make this more efficient using an array but it would be
	// less specific. Its a use vs cost scenario.

	// This is where you can get patient information

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public int getPatientId() {
		return patientID;
	}

	public String getInsuranceInfo() {
		return insuranceInfo;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public boolean getQuarentined() {
		return quarentined;
	}

	public int getSafetyLevel() {
		return safetyLevel;
	}

	public String getDrNotes() {
		return drNotes;
	}

	// From here is simple methods to set the patient information

	public void setFirstName(String fn) {
		this.firstName = fn;
	}

	public void setLastName(String ln) {
		this.lastName = ln;
	}

	public void setRoomNumber(int rn) {
		this.roomNumber = rn;
	}

	public void setPatientId(int pid) {
		this.patientID = pid;
	}

	public void setInsuranceInfo(String insInfo) {
		this.insuranceInfo = insInfo;
	}

	public void setSymptoms(String sympt) {
		this.symptoms = sympt;
	}

	public void setQuarentined(boolean quarentine) {
		this.quarentined = quarentine;
	}

	public void setSafetyLevel(int sl) {
		this.safetyLevel = sl;
	}

	public void setDrNotes(String drNotes) {
		this.drNotes = drNotes;
	}

}
