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
public class MenuTableService {
	 @Autowired
	 MenuTableRepository mRepo;

	 public MenuTableData postMenuTable(MenuTableData mtd) {
	     return mRepo.save(mtd);
	 }

	 public List<MenuTableData> getMenuTable() {
	     return mRepo.findAll();
	 }
	 
	 public MenuTableData updateMenuTable(List<MenuTableData> entities,MenuTableData mtd) {
		 mRepo.deleteAllInBatch(entities);
	     return mRepo.save(mtd);
	 }

	  public void deleteMenuTable(List<MenuTableData> entities) {
	      mRepo.deleteAllInBatch(entities);
	 }
	 
	  public List<MenuTableData> findMenuByMenuAndDate(String menu, Date date) {
		    return mRepo.findAll(
		    		Specification
		    		.where(dateContains(date))
		    		.and(menuContains(menu))
		    		
		    		);
		}	  
	  
	  
	  
	  /**
	     * 指定文字をユーザー名に含むユーザーを検索する。
	     */
	    public  Specification<MenuTableData> menuContains(String menu) {
	        return ObjectUtils.isEmpty(menu) ? null : (root, query, cb) -> {
	            return cb.like(root.get("menu"), "%" + menu + "%");
	        };
	    }
	    
	    /**
	     * 指定文字をユーザー名に含むユーザーを検索する。
	     */
	    public Specification<MenuTableData> dateContains(Date date) {
	        return ObjectUtils.isEmpty(date) ? null : (root, query, cb) -> {
	            return cb.like(root.get("dateCol"), new SimpleDateFormat("yyyy-MM-dd").format(date));
	        };
	    }
}
