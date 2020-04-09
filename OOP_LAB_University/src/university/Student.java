package university;

public class Student implements Comparable{
	
	/*
	 * Attributi
	 */
	private final int MAX_COURSES = 25;
	private String name;
	private String surname;
	private int matricola;
	private Course[] courses = new Course[MAX_COURSES];
	private Exam[] exams = new Exam[MAX_COURSES];
	private float avg;
	private float points;
	
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
	
	
	public String toStringWithPoints() {
		return name + " " + surname + " " + String.valueOf(points);
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
	
	
	public boolean addExam(Exam exam) {
		int i;
		for(i=0; i<exams.length; i++) {
			if(exams[i] == null) {
				exams[i] = exam;
				avg = Exam.calculateAvg(exams);
				this.updatePoints();
				return true;
			}
		}
		return false;
	}
	
	void updatePoints() {
		int i, nExam = 0, nCourse = 0;
		for(i=0; i<exams.length && exams[i] != null; i++, nExam++);
		for(i=0; i<courses.length && courses[i] != null; i++, nCourse++);
		points = avg + (nExam / nCourse * 10);
	}
	
	
	float getAvg(){
		return avg;
	}

	public float getPoints() {
		return points;
	}


	@Override
	public int compareTo(Object arg0) {
		if(this.points > ((Student)arg0).points)
			return -1;
		if(this.points < ((Student)arg0).points)
			return 1;
		return 0;
	}
	
}
