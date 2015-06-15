package kr.jh.sm.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonObject;

@Repository
public class LectureGradeDAO {
	@Autowired
	private JdbcTemplate jt;
	
	private String LECTURE_ID = "lecture_name";
	public Map<String, List<JsonObject>> getGradingFormByProfID(String profID) {
		// Key(lecture name), Value(row)
		final Map<String, List<JsonObject>> result = new HashMap<String, List<JsonObject>>();
		try {
			List<JsonObject> rows = jt.query(
					"select u.name as student_name, l.name as lecture_name, g.grade as grade,"
					+" u.ID as student_id, l.ID as lecture_id"
					+" from sogongdb.user u"
					+" join sogongdb.lecture_grade g on u.ID = g.student_ID"
					+" join sogongdb.lecture l on g.lecture_ID = l.ID"
					+" where l.prof_id = ?",
					new Object[]{profID}, new RowMapper<JsonObject>() {
				@Override
				public JsonObject mapRow(ResultSet rs, int rowCount) throws SQLException {
					JsonObject row = new JsonObject();
					ResultSetMetaData columnInfo = rs.getMetaData();
					for (int i = 1 ; i <= columnInfo.getColumnCount(); ++i) {
						row.addProperty(columnInfo.getColumnLabel(i), rs.getString(i));
					}
					return row;
				}
			});
			// 강좌ID별로 맵에 나누어서 저장
			for (JsonObject e : rows) {
				createKeyIfNeed(result, e);
				result.get(e.get(LECTURE_ID).getAsString()).add(e);
			}
		} catch (Exception e) {e.printStackTrace();}
		return result;
	}

	private void createKeyIfNeed(final Map<String, List<JsonObject>> result, JsonObject e) {
		if (result.get(e.get(LECTURE_ID).getAsString()) == null)
			result.put(e.get(LECTURE_ID).getAsString(), new ArrayList<JsonObject>());
	}

	public void updateGrading(String lectureId, String studentId,
			String grade) {
		try {
			jt.update("update sogongdb.lecture_grade"
					+ " set grade=?"
					+ " where lecture_ID=? and student_ID=?" , grade, lectureId, studentId);
		} catch (Exception e) {e.printStackTrace();}
	}

	public boolean setLecture(String studentId, String lectureId) {
		try {
			jt.update("INSERT INTO sogongdb.lecture_grade (student_ID, lecture_ID, grade)"
					+ " values(?, ?, ?)",
					studentId, lectureId, "NO");
		} catch (Exception e) {e.printStackTrace(); return false;}
		
		return true;
	}

	public List<JsonObject> getGradeById(String student_id) {
		List<JsonObject> result = new ArrayList<JsonObject>();
		try {
			result = jt.query(
					"select u.name as prof_name, l.name as lecture_name,"
					+ "l.year as year, g.grade as grade,"
					+" l.ID as lecture_id, l.school_year as school_year, l.credit as credit, l.max_student as stu_num"
					+" from sogongdb.user u"
					+" join sogongdb.lecture l on u.ID = l.prof_id"
					+" join sogongdb.lecture_grade g on g.lecture_ID = l.ID"
					+" where g.student_ID = ?",
					new Object[]{student_id}, new RowMapper<JsonObject>() {
				@Override
				public JsonObject mapRow(ResultSet rs, int rowCount) throws SQLException {
					JsonObject row = new JsonObject();
					ResultSetMetaData columnInfo = rs.getMetaData();
					for (int i = 1 ; i <= columnInfo.getColumnCount(); ++i) {
						row.addProperty(columnInfo.getColumnLabel(i), rs.getString(i));
					}
					return row;
				}
			});
		} catch (Exception e) {e.printStackTrace();}
		return result;
	}

}
