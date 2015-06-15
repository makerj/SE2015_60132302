package kr.jh.sm;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import kr.jh.sm.dao.LectureGradeDAO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.JsonObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class LectureGradeDAOTest {
	@Autowired
	LectureGradeDAO dao;

	@Test
	public void test() {
		List<JsonObject> result = dao.getGradeById("yunha");
		assertTrue(result.size() >= 1);
	}

}
