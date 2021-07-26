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
public class ReflectionService {
	@Autowired
	ReflectionRepository rRepo;
	
	public ReflectionData postReflection(ReflectionData rd) {
		rRepo.save(rd);
		return rd;
	}
	
	public List<ReflectionData> getReflectionList(){
		return rRepo.findAll();
	}
	
	 public ReflectionData updateReflectionData(List<ReflectionData> entities, ReflectionData rd) {
		 rRepo.deleteAllInBatch(entities);
	     return rRepo.save(rd);
	 }

	  public void deleteMenuTable(List<ReflectionData> entities) {
	      rRepo.deleteAllInBatch(entities);
	 }
	 
	  public List<ReflectionData> findReflectionBydDate(Date date) {
		    return rRepo.findAll(
		    		Specification
		    		.where(dateContains(date))
		    		);
		}	  
	  
	    
	    /**
	     * 指定文字をユーザー名に含むユーザーを検索する。
	     */
	    public Specification<ReflectionData> dateContains(Date date) {
	        return ObjectUtils.isEmpty(date) ? null : (root, query, cb) -> {
	            return cb.like(root.get("dateCol"), new SimpleDateFormat("yyyy-MM-dd").format(date));
	        };
	    }
}
