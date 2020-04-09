package university;

public class Exam {
	
	private int studentId;
	private int courseID;
	private int grade;
	
	
	public Exam(int studentId, int courseID, int grade) {
		this.studentId = studentId;
		this.courseID = courseID;
		this.grade = grade;
	}
	
	
	static float calculateAvg(Exam exams[]) {
		int nExam = 0, sum = 0;
		for(Exam e : exams) {
			if(e == null)
				break;
			++nExam;
			sum += e.getGrade();
		}
		if(nExam == 0)
			return 0;
		return (float) sum / nExam;
	}


	private int getGrade() {
		return grade;
	}
}
