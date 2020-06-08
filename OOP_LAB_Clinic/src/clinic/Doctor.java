package clinic;

import java.util.ArrayList;
import java.util.Collection;

public class Doctor {
    
    private String name;
    private String surname;
    private String ssn;
    private int docID;
    private String specialization;
    private ArrayList<Patient> patients = new ArrayList<>();
    
    public Doctor(String name, String surname, String ssn, int badgeNumber, String specilization) {
	this.name = name;
	this.surname = surname;
	this.ssn = ssn;
	this.docID = badgeNumber;
	this.specialization = specilization;
    }
    
    public void addPatient(Patient patient) {
	patients.add(patient);
    }
    
    public String toString() {
	return String.format("%s %s %s (%d) [%s]", name, surname, ssn, docID, specialization);
    }

    public int getId() {
	return docID;
    }
    
    public Collection<String> getPatients(){
	ArrayList<String> patientString = new ArrayList<>();
	patients.stream().forEach(p -> patientString.add(p.toString()));
	return patientString;
    }

}
