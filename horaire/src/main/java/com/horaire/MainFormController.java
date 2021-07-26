package com.horaire;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
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
@RequestMapping("/mainform") 
public class MainFormController {
	
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
	RequMenuRepository reqRepo;
	
	@Autowired
	RequMenuService reqService;
	  
	  // アクセス先URIに関連する
	@GetMapping
	    public ModelAndView pageMain(ModelAndView mav) {
	        mav.setViewName("MainForm"); // HTMLファイル名を拡張子無しで指定する
	        ArrayList<MenuTableData> list = (ArrayList<MenuTableData>) mService.getMenuTable();
			mav.addObject("menuData",list);
			ArrayList<StartingTimeData> sList = (ArrayList<StartingTimeData>) sService.getStartingTime();
			mav.addObject("startingTime",sList);
			ArrayList<ReflectionData> rList = (ArrayList<ReflectionData>) rService.getReflectionList();
			mav.addObject("refList", rList);
			ArrayList<RequMenuData> reqList = (ArrayList<RequMenuData>) reqService.getReqMenuList();
			mav.addObject("reqList", reqList);
	        return mav;
	   }
	  
	@PostMapping(params="addData")
		public @ResponseBody String addMenuData (@RequestParam(name = "thisDate",required = true) String dateSt, @RequestParam(name = "menu",required = true) String menu, @RequestParam(name="detail",required = true) String detail, @RequestParam(name="st",required = true) String st, @RequestParam(name="ft",required = true) String ft) throws ParseException {
			
			st = st+":00";
			ft = ft+":00";
			
			var data = new MenuTableData();
            Date date = Date.valueOf(dateSt);
			data.setDateCol(date.toString());
			data.setMenu(menu);
			data.setDetail(detail);
			data.setSt(Time.valueOf(st));
			data.setFt(Time.valueOf(ft));
			
			mService.postMenuTable(data);
			return "saved";
		}
		
		@PostMapping(params="editData")
		public @ResponseBody String editMenuData (@RequestParam(name = "edThisDate",required = true) String dateSt, @RequestParam(name = "edMenu",required = true) String menu, @RequestParam(name="edDetail",required = true) String detail, @RequestParam(name="edSt",required = true) String st, @RequestParam(name="edFt",required = true) String ft) throws ParseException {
			
			st = st+":00";
			ft = ft+":00";
			Date date = Date.valueOf(dateSt);
			
			var data = new MenuTableData();
			data.setDateCol(date.toString());
			data.setMenu(menu);
			data.setDetail(detail);
			data.setSt(Time.valueOf(st));
			data.setFt(Time.valueOf(ft));
			
			var entities = mService.findMenuByMenuAndDate(menu, date);
			
			mService.updateMenuTable(entities, data);
			
			return "edited";
		}
		
		@PostMapping(params="deleteData")
		public @ResponseBody String deleteMenuData (@RequestParam(name = "edThisDate",required = true) String dateSt, @RequestParam(name = "edMenu",required = true) String menu, @RequestParam(name="edDetail",required = true) String detail, @RequestParam(name="edSt",required = true) String st, @RequestParam(name="edFt",required = true) String ft) throws ParseException {
			
			st = st+":00";
			ft = ft+":00";
			Date date = Date.valueOf(dateSt);
			
			var data = new MenuTableData();
			data.setDateCol(date.toString());
			data.setMenu(menu);
			data.setDetail(detail);
			data.setSt(Time.valueOf(st));
			data.setFt(Time.valueOf(ft));
			
			var entities = mService.findMenuByMenuAndDate(menu, date);
			
			mService.deleteMenuTable(entities);
			
			return "edited";
		}
		
		@PostMapping(params="refForm")
		public @ResponseBody String addRefData (@RequestParam(name = "refThisDate",required = true) String dateSt, @RequestParam(name = "ref",required = true) String ref){
			
			var data = new ReflectionData();
            Date date = Date.valueOf(dateSt);
			data.setDate(date.toString());
			data.setReflection(ref);
			
			rService.postReflection(data);
			return "saved";
		}
		
}
