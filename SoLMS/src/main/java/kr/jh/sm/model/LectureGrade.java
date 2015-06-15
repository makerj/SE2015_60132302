package kr.jh.sm.model;

public class LectureGrade {
	private String student_ID;
	private int lecture_ID;
	private String grade;

	public String getStudent_ID() {
		return student_ID;
	}

	public void setStudent_ID(String student_ID) {
		this.student_ID = student_ID;
	}

	public int getLecture_ID() {
		return lecture_ID;
	}

	public void setLecture_ID(int lecture_ID) {
		this.lecture_ID = lecture_ID;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}
