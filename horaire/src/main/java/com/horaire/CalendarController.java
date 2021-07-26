package com.horaire;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.horaire.db.MenuTableRepository;
import com.horaire.db.MenuTableService;
import com.horaire.db.StartingTimeData;
import com.horaire.db.StartingTimeRepository;
import com.horaire.db.StartingTimeService;
import com.horaire.db.UserRepository;
import com.horaire.db.UserService;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
	
	@Autowired
	UserRepository uRepo;
	
	@Autowired
	UserService uService;
	
	@Autowired
	MenuTableRepository mRepo;
	
	@Autowired
	MenuTableService mService;
	
	@Autowired
	StartingTimeRepository sRepo;
	
	@Autowired
	StartingTimeService sService;
	
	
	 final ObjectMapper objectMapper;
	 
	 public CalendarController( ObjectMapper om) {
		this.objectMapper = om;
	}	  
	
	 
	 @GetMapping // アクセス先URIに関連する
	    public ModelAndView calendarPage(ModelAndView mav) {
	        mav.setViewName("Calendar"); // HTMLファイル名を拡張子無しで指定する
	        
	        ArrayList<StartingTimeData> list = (ArrayList<StartingTimeData>) sService.getStartingTime();
			mav.addObject("startingTime",list);
			
			
	        return mav;
	    }
	  @PostMapping(params="send")
		public @ResponseBody String addNewUser (@RequestParam(name = "setDate",required = true) String dateSt, @RequestParam(name="setTime",required = true) String starting_time) {
			
			starting_time = starting_time+":00";
			
			StartingTimeData n = new StartingTimeData();
			Time time =Time.valueOf(starting_time);
			
			n.setDate(dateSt);
			n.setStarting_time(time);
			
			sService.postStartingTime(n);
			return "saved";
		}
	  @PostMapping(params="edit")
			public @ResponseBody String editNewUser (@RequestParam(name = "editDate",required = true) String dateSt, @RequestParam(name="editTime",required = true) String starting_time) {
				
				starting_time = starting_time+":00";
				
				StartingTimeData n = new StartingTimeData();
				
				Date date = Date.valueOf(dateSt);
				Time time =Time.valueOf(starting_time);
				
				n.setDate(dateSt);
				n.setStarting_time(time);
				
				
				var entities = sService.findByDate(date);
				
				sService.updateStartingTime(entities,n);
				return "edited";
			}
	
	  @PostMapping(params="delete")
		public @ResponseBody String daleteNewUser (@RequestParam(name = "editDate",required = true) String dateSt) {
			
		  Date date = Date.valueOf(dateSt);
		  
		  var entities = sService.findByDate(date);
		  
		  sService.deleteStartingTime(entities);
		  
			return "deleted";
		}
	  
	
	  
}