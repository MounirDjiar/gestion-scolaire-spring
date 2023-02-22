package com.groupe2.gestionscolaire.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupe2.gestionscolaire.model.Clazz;

public interface ClazzDao extends JpaRepository <Clazz, Long> {

}
