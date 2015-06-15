package kr.jh.sm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.jh.sm.model.Lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LectureDAO {
	@Autowired
	private JdbcTemplate jt;
	
	//'0001', '소프트웨어공학', '2015', '3', '40', '3', '최성운짱짱'
	public boolean addLecture(int lecID, String lecName, int year, int schYear, int maxSt, String prof, int credit){
		try{
			jt.update("INSERT INTO sogongDB.Lecture VALUES(?,?,?,?,?,?,?)",
					lecID, lecName, year, schYear, maxSt, credit, prof);
			System.out.println("insert 성공!");
		} catch (DuplicateKeyException e) {System.out.println("중복되는 과목입니다."); return false;}
		catch(Exception e) {e.printStackTrace(); return false;}
		return true;
	}
	
	public List<Lecture> getLectureByProfessorUID(String profUID) {
		List<Lecture> lecs = new ArrayList<Lecture>();
		try {
			lecs = jt.query("SELECT * FROM sogongDB.LECTURE WHERE prof_id=?", new Object[]{profUID}, new RowMapper<Lecture>() {
				@Override
				public Lecture mapRow(ResultSet rs, int rowCount) throws SQLException {
					Lecture lec = new Lecture();
					lec.setCredit(rs.getInt("credit"));
					lec.setID(rs.getInt("ID"));
					lec.setMax_student(rs.getInt("max_student"));
					lec.setName(rs.getString("name"));
					lec.setProf_id(rs.getString("prof_id"));
					lec.setSchool_year(rs.getInt("school_year"));
					lec.setYear(rs.getInt("year"));
					return lec;
				}
			});
		} catch (Exception e) {e.printStackTrace();}
		
		return lecs;
	}

	public List<Lecture> getLecture() {
		List<Lecture> lecs = new ArrayList<Lecture>();
		try {
			lecs = jt.query("SELECT * FROM sogongDB.LECTURE", new Object[]{}, new RowMapper<Lecture>() {
				@Override
				public Lecture mapRow(ResultSet rs, int rowCount) throws SQLException {
					Lecture lec = new Lecture();
					lec.setCredit(rs.getInt("credit"));
					lec.setID(rs.getInt("ID"));
					lec.setMax_student(rs.getInt("max_student"));
					lec.setName(rs.getString("name"));
					lec.setProf_id(rs.getString("prof_id"));
					lec.setSchool_year(rs.getInt("school_year"));
					lec.setYear(rs.getInt("year"));
					return lec;
				}
			});
		} catch (Exception e) {e.printStackTrace();}
		
		return lecs;
	}
}
