package university;

public class Course {
	/*
	 * Attributi
	 */
	private static final int MAX_STUDENT = 100;
	private int code;
	private String professor;
	private String name;
	private Student[] attendees = new Student[MAX_STUDENT];
	
	/*
	 * costruttore
	 */
	public Course(String name, String professor, int code) {
		this.name = name; 
		this.professor = professor;
		this.code = code;
	}
	
	/*
	 * metodi
	 */
	public String toString(){
		return code + ", " + name + ", " + professor;
	}

	
	public boolean addAttendee(Student student) {
		for(int i=0; i<attendees.length; i++) {
			if(attendees[i]==null) {
				attendees[i] = student;
				return true;
			}
		}
		return false;
	}
	
	
	public String attendeesListToString() {
		StringBuffer buf = new StringBuffer();
		for(int i=0; i<attendees.length && attendees[i] != null; ++i) {
			buf.append(attendees[i].toString() + "\n");
		}
		return buf.toString();
	}
	
	
	public int getCode() {
		return code;
	}
	
	
}
