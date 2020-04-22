package university;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniversityExt extends University {
	
	

	
	public UniversityExt(String name) {
		super(name);
		// Example of logging
		logger.info("Creating extended university object");
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
		for(i=0; i<3 && i<copy.length && copy[i].getPoints() > 0; i++)
			buf.append(copy[i].toStringWithPoints() + '\n');
	
		return buf.toString();
	}
}
