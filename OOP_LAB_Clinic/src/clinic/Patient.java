package clinic;

public class Patient {
    private String name;
    private String surname;
    private String ssn;
    private Doctor doctor;

    public Patient(String name, String surname, String ssn) {
	this.name = name;
	this.surname = surname;
	this.ssn = ssn;
    }

  
    public Doctor getDoctor() throws NoSuchDoctor{
	if(doctor == null)
	    throw new NoSuchDoctor("Nessun dottore assegnato");
	return doctor;
    }
    @Override
    public String toString() {
	return String.format("%s %s (%s)", surname, name, ssn);
    }
    
    
    public void setDoctor(Doctor doctor) {
	this.doctor = doctor;
    }
}
