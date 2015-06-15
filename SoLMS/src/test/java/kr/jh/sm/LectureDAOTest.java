package kr.jh.sm;

import static org.junit.Assert.*;

import java.util.List;

import kr.jh.sm.dao.LectureDAO;
import kr.jh.sm.model.Lecture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class LectureDAOTest {
	@Autowired
	private LectureDAO dao;

	@Test(timeout = 3000)
	public void getLectureByProfessorUIDTest() {
		List<Lecture> lecs = dao.getLectureByProfessorUID("choi");
		for (Lecture e : lecs)
			if (e.getID() == 1) {
				assertTrue(e.getMax_student() == 40);
				assertTrue(e.getCredit() == 3);
				assertTrue(e.getSchool_year() == 3);
			}
		assertTrue(lecs.size() >= 1);
	}
}
