package kr.jh.sm.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.jh.sm.dao.UserDAO;
import kr.jh.sm.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignController {
	private static final Logger logger = LoggerFactory.getLogger(SignController.class);
	
	@Autowired
	private UserDAO userDAO;

	public static final String login = "SignController/login";
	@RequestMapping(value = login, method = RequestMethod.POST)
	public String login(HttpSession session, HttpServletRequest request,
			@RequestParam("user_id") String id,
			@RequestParam("user_password") String password) throws IOException {

		User user = userDAO.findUser(id, password);
		if (user == null) {
			logger.info("Try again");
			return "error";
		}
		session.setAttribute("id", user.getID());
		session.setAttribute("name", user.getName());
		session.setAttribute("authority", user.getAuthority());
		
		if(session.getAttribute("authority").equals("prof")){
			return "login_prof";
		} else if(session.getAttribute("authority").equals("student")){
			return "login_student";
		}
		return "home";
	}
	
	@RequestMapping(value="SignController/logOut", method = RequestMethod.GET)
	public String logOut(HttpSession session, HttpServletRequest request){
		session.invalidate();
		
		return "redirect:/";
	}

	@RequestMapping(value = "SignController/signUp", method = RequestMethod.POST)
	public String signUp(HttpSession session, HttpServletRequest request,
			@RequestParam("user_id") String id,
			@RequestParam("user_password") String password,
			@RequestParam("user_name") String name,
			@RequestParam("user_authority") String ath) throws IOException {
		if (!userDAO.addUser(id, password, name, ath)) {
			return "error";
		}
		
		return "home";
	}
}
