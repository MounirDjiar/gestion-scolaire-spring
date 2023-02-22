package com.groupe2.gestionscolaire.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupe2.gestionscolaire.model.Teacher;

public interface TeacherDao extends JpaRepository<Teacher, Long>{

}
