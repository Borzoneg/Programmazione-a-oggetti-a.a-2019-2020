package university;

import java.util.logging.Logger;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {

	protected static final int START_POINT_MATRICOLE = 10000;
	protected static final int START_POINT_COURSE_CODES = 10;
	private static final int MAX_COURSES = 50;
	private static final int MAX_STUDENTS = 1000;
	private String name;
	private String rectorName;
	protected Course[] courses = new Course[MAX_COURSES];
	protected Student[] students = new Student[MAX_STUDENTS];
	protected final static Logger logger = Logger.getLogger("University");
	
	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		this.name = name;
	}
	
	
	/**
	 * Activates a new course with the given teacher
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		for(int i=0; i<courses.length; i++) {
			if(courses[i]==null) {
				courses[i] = new Course(title, teacher, i + START_POINT_COURSE_CODES);
				logger.info(String.format("New course activated: %d %s %s", i + START_POINT_COURSE_CODES, title, teacher));
				return courses[i].getCode();
			}
		}
		return -1;
	}
	
	
	
	/**
	 * Enroll a student in the university
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * @return
	 */
	public int enroll(String first, String last){
		for(int i=0; i<students.length; ++i) {
			if(students[i] == null) {
				students[i] = new Student(first, last, i + START_POINT_MATRICOLE);
				logger.info(String.format("New student, enrolled: %d %s %s", i + START_POINT_MATRICOLE, first, last));
				return students[i].getMatricola();
			}
		}
		return -1;
	}
	
	
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		if(!students[studentID - START_POINT_MATRICOLE].addCourse(courses[courseCode - START_POINT_COURSE_CODES]))
			System.out.println("Raggiunto il limite massimo di corsi per lo studente");
		if(!courses[courseCode - START_POINT_COURSE_CODES].addAttendee(students[studentID - START_POINT_MATRICOLE]))
			System.out.println("Raggiunto il limite massimo di studenti iscritti al corso");
		
		logger.info(String.format("Student %d signed up for course %d", studentID, courseCode));
	}
	
	
	public void exam(int studentId, int courseID, int grade) {
		Exam exam = new Exam(studentId, courseID, grade);
		students[studentId - START_POINT_MATRICOLE].addExam(exam);
		courses[courseID - START_POINT_COURSE_CODES].addExam(exam);
		logger.info(String.format("Student %d took an exam in course %d with grade %d", 
				studentId + START_POINT_MATRICOLE, courseID + START_POINT_COURSE_CODES, grade));
	}
	
	
	/**
	 * Getter for the name of the university
	 * @return name of university
	 */
	public String getName(){
		return name;
	}
	
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		rectorName = first + " " + last;
	}
	
	
	/**
	 * Retrieves the rector of the university
	 * 
	 * @return
	 */
	public String getRector(){
		return rectorName;
	}
	
	
	/**
	 * Retrieves the information for a given student
	 * 
	 * @param id the id of the student
	 * @return information about the student
	 */
	public String student(int id){
		return students[id - START_POINT_MATRICOLE].toString();
	}
	

	/**
	 * Retrieve the information for a given course
	 * 
	 * @param code unique code of the course
	 * @return information about the course
	 */
	public String course(int code){
		return courses[code - START_POINT_COURSE_CODES].toString();
	}
	
	
	/**
	 * Retrieve a list of attendees
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		return courses[courseCode - START_POINT_COURSE_CODES].attendeesListToString();
	}

	
	/**
	 * Retrieves the study plan for a student
	 * 
	 * @param studentID id of the student
	 * @return list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		return students[studentID - START_POINT_MATRICOLE].studyPlanToString();
	}
}
