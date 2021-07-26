package com.horaire;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.horaire.db.MenuTableData;
import com.horaire.db.MenuTableRepository;
import com.horaire.db.MenuTableService;
import com.horaire.db.ReflectionData;
import com.horaire.db.ReflectionRepository;
import com.horaire.db.ReflectionService;
import com.horaire.db.RequMenuData;
import com.horaire.db.RequMenuRepository;
import com.horaire.db.RequMenuService;
import com.horaire.db.StartingTimeData;
import com.horaire.db.StartingTimeRepository;
import com.horaire.db.StartingTimeService;
import com.horaire.db.UserRepository;
import com.horaire.db.UserService;

@Controller
@RequestMapping("/memMainform") 
public class MemMainFormController {
	
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
	
	@Autowired
	ReflectionRepository rRepo;
	
	@Autowired
	ReflectionService rService;
	
	@Autowired
	RequMenuRepository requRepo;
	
	@Autowired
	RequMenuService requService;
	
	  
	  // アクセス先URIに関連する
	@GetMapping
	    public ModelAndView pageMain(ModelAndView mav) {
	        mav.setViewName("MemMainForm"); // HTMLファイル名を拡張子無しで指定する
	        ArrayList<MenuTableData> list = (ArrayList<MenuTableData>) mService.getMenuTable();
			mav.addObject("menuData",list);
			ArrayList<StartingTimeData> sList = (ArrayList<StartingTimeData>) sService.getStartingTime();
			mav.addObject("startingTime",sList);
			ArrayList<ReflectionData> rList = (ArrayList<ReflectionData>) rService.getReflectionList();
			mav.addObject("refList", rList);
	        return mav;
	    }
	  
	
		
		@PostMapping(params="request")
		public @ResponseBody String editMenuData (@RequestParam(name = "requThisDate",required = true) String dateSt, @RequestParam(name = "requMenu",required = true) String menu, @RequestParam(name="requDetail",required = true) String detail,@RequestParam(name="requTime",required = true) String time){
			
			Date date = Date.valueOf(dateSt);
			
			var data = new RequMenuData();
			data.setDate(date.toString());
			data.setMenu(menu);
			data.setDetail(detail);
			data.setTime(Integer.valueOf(time));
			
			requService.postMenuTable(data);
			
			return "Posted";
		}
		
		
		@PostMapping(params="refForm")
		public @ResponseBody String addRefData (@RequestParam(name = "refThisDate",required = true) String dateSt, @RequestParam(name = "ref",required = true) String ref){
			
			var data = new ReflectionData();
			System.out.println(dateSt +"a");
            Date date = Date.valueOf(dateSt);
			data.setDate(date.toString());
			data.setReflection(ref);
			
			rService.postReflection(data);
			return "saved";
		}
		
}
