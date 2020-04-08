package university;

public class Student {
	/*
	 * Attributi
	 */
	private final int MAX_COURSES = 25;
	private String name;
	private String surname;
	private int matricola;
	private Course[] courses = new Course[MAX_COURSES];
	
	/*
	 * Costruttore
	 */
	public Student(String name, String surname, int matricola) {
		this.name = name;
		this.surname = surname;
		this.matricola = matricola;
	}
	
	
	/* 
	 * Metodi
	 */
	public String toString() {
		return name + " " + surname + " " + String.valueOf(matricola);
	}
	
	
	public String studyPlanToString() {
		StringBuffer buf = new StringBuffer();
		for(int i=0; i<courses.length && courses[i] != null; i++) {
			buf.append(courses[i].toString() + "\n");
		}
		return buf.toString();
	}
	
	
	public boolean addCourse(Course course) {
		for(int i=0; i<courses.length; i++) {
			if(courses[i] == null) {
				courses[i] = course;
				return true;
			}
		}
		return false;
	}

	
	public int getMatricola() {
		return matricola;
	}
}
