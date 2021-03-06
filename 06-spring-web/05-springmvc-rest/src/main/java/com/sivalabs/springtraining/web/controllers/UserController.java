/**
 * 
 */
package com.sivalabs.springtraining.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sivalabs.springtraining.entities.User;
import com.sivalabs.springtraining.services.UserService;

/**
 * @author Siva
 *
 */
@Controller
public class UserController 
{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String loginForm(Model model) {
		User user = new User();
		user.setEmail("admin@gmail.com");
		model.addAttribute("user", user);
		return "login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") User user, BindingResult error, HttpServletRequest request) {
		String view = "login";
		User loginUser = userService.login(user.getEmail(), user.getPassword());
		if(loginUser != null)
		{
			request.getSession().setAttribute("LOGIN_USER", loginUser);
			view = "redirect:/welcome";
		}
		else
		{
			request.setAttribute("error", "Invalid Email and Password");
		}
		return new ModelAndView(view);
	}
	
	@RequestMapping("welcome")
	public String welcome() {
		return "welcome";
	}
	
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
