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
public class StartingTimeService {
	 @Autowired
	 StartingTimeRepository sRepo;

	 public StartingTimeData postStartingTime(StartingTimeData std) {
	     return sRepo.save(std);
	 }

	 public List<StartingTimeData> getStartingTime() {
	     return sRepo.findAll();
	 }
	 
	 public StartingTimeData updateStartingTime(List<StartingTimeData> entities, StartingTimeData std) {
		 sRepo.deleteAllInBatch(entities);
		 return sRepo.save(std);
	 }

	  public void deleteStartingTime(List<StartingTimeData> entities) {
	      sRepo.deleteAllInBatch(entities);
	 }
	  
	  public List<StartingTimeData> findByDate(Date date) {
		    return sRepo.findAll(
		    		Specification
		    		.where(dateContains(date))
		    		);
		}	
	  
	  
	    /**
	     * 指定文字を日付に含むユーザーを検索する。
	     */
	    public static Specification<StartingTimeData> dateContains(Date date) {
	        return ObjectUtils.isEmpty(date) ? null : (root, query, cb) -> {
	            return cb.like(root.get("date"), new SimpleDateFormat("yyyy-MM-dd").format(date));
	        };
	    }
}
