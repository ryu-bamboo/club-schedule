package com.horaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.horaire.db.UserData;
import com.horaire.db.UserRepository;
import com.horaire.db.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	UserRepository uRepo;
	
	@Autowired
	UserService uService;
	
	@GetMapping
	public ModelAndView page(ModelAndView mav) {
		mav.setViewName("register");
		var dataList = uService.findAll();
		mav.addObject("userList",dataList);
		return mav;
	}
	
	@PostMapping
	public @ResponseBody String addUserData (@RequestParam(name = "name",required = true) String name, @RequestParam(name = "pass",required = true) String password) {
		
		var dataList = uService.findAll();
		
		var registerData = new UserData();
		registerData.setName(name);
		registerData.setPassword(password);
		
		for(var data:dataList) {
			if(data.getName().equals(name)) {
				//重複した登録の処理
				System.out.println("a");
				break;
			}
			System.out.println("c");
			uService.postUser(registerData);
		}
		
		return "saved";
	}
	
}
