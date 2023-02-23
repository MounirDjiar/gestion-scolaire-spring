package com.groupe2.gestionscolaire.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupe2.gestionscolaire.model.Teacher;

public interface TeacherDao extends JpaRepository<Teacher, Long>{

	public List<Teacher> findBySchoolId(Long id);

}
