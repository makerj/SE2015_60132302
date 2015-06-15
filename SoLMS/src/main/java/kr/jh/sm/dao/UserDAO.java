package kr.jh.sm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import kr.jh.sm.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	@Autowired
	private JdbcTemplate jt;

	public boolean addUser(String ID, String PW, String name, String author) {
		try{
			jt.update("INSERT INTO sogongDB.USER VALUES(?,?,?,?)",
					ID, PW, name, author);
			System.out.println("insert 성공!");
		} catch (DuplicateKeyException e) {System.out.println("중복되는 아이디입니다."); return false;}
		catch(Exception e) {e.printStackTrace(); return false;}
		return true;
	}

	public User findUser(String ID, String PW) {
		User found = null;
		try {
			found = jt.queryForObject(
					"SELECT * FROM sogongDB.USER WHERE ID=? and PW=?",
					new Object[] { ID, PW}, new RowMapper<User>() {
						@Override
						public User mapRow(ResultSet rs, int count)
								throws SQLException {
							User u = new User();
							u.setID(rs.getString("ID"));
							u.setPW(rs.getString("PW"));
							u.setName(rs.getString("name"));
							u.setAuthority(rs.getString("authority"));
							return u;
						}
					});
		} catch (Exception e) {/* no result */}
		return found;

	}

	public boolean delUser(String ID) {
		try {
			jt.update("DELETE FROM sogongDB.USER WHERE ID=?",ID);
		} catch (Exception e) {/* no user */ System.out.println("user가 존재하지 않습니다."); return false;}
		return true;
	}
}
