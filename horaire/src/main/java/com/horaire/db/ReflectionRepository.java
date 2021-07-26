package com.horaire.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReflectionRepository extends JpaRepository<ReflectionData, Integer> ,JpaSpecificationExecutor<ReflectionData>{
	
}
