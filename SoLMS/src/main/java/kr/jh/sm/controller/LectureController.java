package kr.jh.sm.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.jh.sm.dao.LectureDAO;
import kr.jh.sm.dao.LectureGradeDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LectureController {
	@Autowired
	private LectureDAO lec;
	@Autowired
	private LectureGradeDAO lecGrade;
	
	@RequestMapping(value="LectureController/createLecture", method=RequestMethod.POST)
	public String createLecture(HttpSession session, HttpServletRequest request,
	@RequestParam("lecture_number") int lecID,
	@RequestParam("lecture_name") String lecName,
	@RequestParam("year") int year,
	@RequestParam("school_year") int schYear,
	@RequestParam("student_number") int maxSt,
	@RequestParam("credit") int crd) throws IOException {
		if(!lec.addLecture(lecID, lecName, year, schYear, maxSt, (String)session.getAttribute("name"), crd)){return "error";}
		return "login_prof";
	}
	
	@RequestMapping(value="LectureController/grading", method=RequestMethod.GET)
	public ModelAndView grading(HttpSession session) {
		ModelAndView mv = new ModelAndView("grading");
		mv.addObject("data", lecGrade.getGradingFormByProfID(session.getAttribute("id").toString()));
		return mv;
	}
	
	@RequestMapping(value="LectureController/setGrade", method=RequestMethod.POST)
	public String setGrade(HttpSession session, HttpServletRequest request,
			@RequestParam("lecture_id") String lectureId,
			@RequestParam("student_id") String studentId,
			@RequestParam("grade") String grade,
			@RequestParam("session_id") String sessionId) {
		// Prevent CSRF Attack
		if (!session.getAttribute("id").equals(sessionId))
			return "redirect:/";
		
		lecGrade.updateGrading(lectureId, studentId, grade);
		
		return "redirect:/LectureController/grading";
	}
	
	@RequestMapping(value="LectureController/enroll_class", method=RequestMethod.GET)
    public ModelAndView enrollClass(HttpSession session){
		ModelAndView mv = new ModelAndView("enroll_class");
		mv.addObject("enroll", lec.getLecture());
		return mv;
	}
	
	@RequestMapping(value="LectureController/setLecture", method=RequestMethod.POST)
	public String setLecture(HttpSession session,
			@RequestParam("lecture_id") String lectureId){
		String studentId = (String) session.getAttribute("id");
		if(!lecGrade.setLecture(studentId, lectureId)){return "error";}
		
		return "redirect:/LectureController/enroll_class";
	}
	
	@RequestMapping(value="LectureController/confirm_grade", method=RequestMethod.GET)
	public ModelAndView confirm_grade(HttpSession session){
		ModelAndView mv = new ModelAndView("confirm_grade");
		mv.addObject("confirm", lecGrade.getGradeById((String)session.getAttribute("id")));
		return mv;
	}
}
