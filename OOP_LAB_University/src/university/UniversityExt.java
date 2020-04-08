package university;

import java.util.logging.Logger;

public class UniversityExt extends University {
	
	Student top[] = new Student[3];
	
	private final static Logger logger = Logger.getLogger("University");

	
	public UniversityExt(String name) {
		super(name);
		// Example of logging
		logger.info("Creating extended university object");
	}
	
	
	public void exam(int studentId, int courseID, int grade) {
		Exam exam = new Exam(studentId, courseID, grade);
		students[studentId - START_POINT_MATRICOLE].addExam(exam);
		courses[courseID - START_POINT_COURSE_CODES].addExam(exam);
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
	
	
	public String topThreeStudents() {
		// TODO
		return "USARE UN COMPARATOR PER ORDINARE LA LISTA";
	}
}
