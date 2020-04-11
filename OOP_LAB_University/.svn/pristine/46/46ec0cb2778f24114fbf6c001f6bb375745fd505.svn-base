package university;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class UniversityExt extends University {
	
	private final static Logger logger = Logger.getLogger("University");

	
	public UniversityExt(String name) {
		super(name);
		// Example of logging
		logger.info("Creating extended university object");
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
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		if(!students[studentID - START_POINT_MATRICOLE].addCourse(courses[courseCode - START_POINT_COURSE_CODES]))
			System.out.println("Raggiunto il limite massimo di corsi per lo studente");
		if(!courses[courseCode - START_POINT_COURSE_CODES].addAttendee(students[studentID - START_POINT_MATRICOLE]))
			System.out.println("Raggiunto il limite massimo di studenti iscritti al corso");
		
		logger.info(String.format("Student %d signed up for course %d", studentID + START_POINT_MATRICOLE, courseCode + START_POINT_COURSE_CODES));
	}
	
	
	public void exam(int studentId, int courseID, int grade) {
		Exam exam = new Exam(studentId, courseID, grade);
		students[studentId - START_POINT_MATRICOLE].addExam(exam);
		courses[courseID - START_POINT_COURSE_CODES].addExam(exam);
		logger.info(String.format("Student %d took an exam in course %d with grade %d", 
				studentId + START_POINT_MATRICOLE, courseID + START_POINT_COURSE_CODES, grade));
	}

	
	public String studentAvg(int studentId) {
		float avg = students[studentId - START_POINT_MATRICOLE].getAvg();
		if(avg == 0) {
			return "Student " + String.valueOf(studentId) +  " hasn't taken any exams";
		}
		return String.format("Student %d: %.2f", studentId, avg);				
	}
	
	
	public String courseAvg(int courseId) {
		float avg = courses[courseId - START_POINT_COURSE_CODES].getAvg();
		if(avg == 0) {
			return "No student has taken the exam in " + courses[courseId - START_POINT_COURSE_CODES].getName();
		}
		return String.format("The average for the course %s is: %.2f", courses[courseId - START_POINT_COURSE_CODES].getName(), avg);		
	}
	
	
	public Student[] getStudentsList() {
		return students;
	}
	
	
	public String topThreeStudents() {
		int i;
		Student[] copy;
		List<Student> studentsList = new ArrayList<Student>();
		StringBuffer buf = new StringBuffer();
		
		for(Student s : students) {
			if(s==null)
				break;
			studentsList.add(s);
		}
		copy = studentsList.toArray(new Student[studentsList.size()]);
		Arrays.sort(copy);
		
		for(i=0; i<3 && i<copy.length; i++) {
			if(copy[i]==null)
				break;
			buf.append(copy[i].toStringWithPoints() + '\n');
		}

		return buf.toString();
	}
}
