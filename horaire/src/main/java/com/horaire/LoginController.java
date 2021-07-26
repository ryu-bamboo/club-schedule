package com.horaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.horaire.db.UserRepository;
import com.horaire.db.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	UserRepository uRepo;
	
	@Autowired
	UserService uService;
	
	@GetMapping
	public ModelAndView page(ModelAndView mav) {
		mav.setViewName("login");
		var list = uService.findAll();
		mav.addObject("userList", list);
		return mav;
	}
	
	
}
