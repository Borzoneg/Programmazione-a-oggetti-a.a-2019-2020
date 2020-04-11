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
	
	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		this.name = name;
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
