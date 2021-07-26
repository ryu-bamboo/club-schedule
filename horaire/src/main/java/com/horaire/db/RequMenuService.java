package com.horaire.db;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Transactional
public class RequMenuService {
	 @Autowired
	 RequMenuRepository reqRepo;

	 public RequMenuData postMenuTable(RequMenuData reqd) {
	     return reqRepo.save(reqd);
	 }

	 public List<RequMenuData> getReqMenuList() {
	     return reqRepo.findAll();
	 }
	 
	 
	  public List<RequMenuData> findMenuByDate(Date date) {
		    return reqRepo.findAll(
		    		Specification
		    		.where(dateContains(date))
		    		
		    		);
		}	  
	  
	  
	  
	  
	    /**
	     * 指定文字をユーザー名に含むユーザーを検索する。
	     */
	    public Specification<RequMenuData> dateContains(Date date) {
	        return ObjectUtils.isEmpty(date) ? null : (root, query, cb) -> {
	            return cb.like(root.get("date"), new SimpleDateFormat("yyyy-MM-dd").format(date));
	        };
	    }
}
