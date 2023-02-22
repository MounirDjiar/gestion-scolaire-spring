package com.groupe2.gestionscolaire.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupe2.gestionscolaire.model.School;

public interface SchoolDao extends JpaRepository<School, Long>{
	
}
