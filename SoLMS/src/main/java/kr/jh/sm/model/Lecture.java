package kr.jh.sm.model;

public class Lecture {
	private int ID;
	private String name;
	private int year;
	private int school_year;
	private int max_student;
	private int credit;
	private String prof_id;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSchool_year() {
		return school_year;
	}

	public void setSchool_year(int school_year) {
		this.school_year = school_year;
	}

	public int getMax_student() {
		return max_student;
	}

	public void setMax_student(int max_student) {
		this.max_student = max_student;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getProf_id() {
		return prof_id;
	}

	public void setProf_id(String prof_id) {
		this.prof_id = prof_id;
	}
}
