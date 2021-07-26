package com.horaire;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.horaire.db.StartingTimeData;
import com.horaire.db.StartingTimeService;

@Controller
@RequestMapping("/memCalendar")
public class MemCalendarController {
	
	
	
	@Autowired
	StartingTimeService sService;
	
	
	 final ObjectMapper objectMapper;
	 
	 public MemCalendarController( ObjectMapper om) {
		this.objectMapper = om;
	}	  
	
	 
	 @GetMapping // アクセス先URIに関連する
	    public ModelAndView calendarPage(ModelAndView mav) {
	        mav.setViewName("MemCalendar"); // HTMLファイル名を拡張子無しで指定する
	        
	        ArrayList<StartingTimeData> list = (ArrayList<StartingTimeData>) sService.getStartingTime();
			mav.addObject("startingTime",list);
			
			
	        return mav;
	    }
	 

	  
	
	  
}