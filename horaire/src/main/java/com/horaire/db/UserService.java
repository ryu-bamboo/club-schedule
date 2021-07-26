package com.horaire.db;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Transactional
public class UserService {
	 @Autowired
	 UserRepository uRepo;

	 public UserData postUser(UserData ud) {
	     return uRepo.save(ud);
	 }

	 public List<UserData> getUser() {
	     return uRepo.findAll();
	 }
	 
	 public UserData updateUser(UserData ud) {
	      return uRepo.save(ud);
	 }

	 public void deleteUsere(Integer id) {
	      uRepo.deleteById(id);
	}
	 
	 public List<UserData> findAll(){
		 return uRepo.findAll();
	 }
	 
	 public List<UserData> findByUserName(String name){
		 return uRepo.findAll(Specification.where(nameContains(name)));
	 }
	 
	 public  Specification<UserData> nameContains(String name) {
	        return ObjectUtils.isEmpty(name) ? null : (root, query, cb) -> {
	            return cb.like(root.get("name"), "%" + name + "%");
	        };
	    }
}
