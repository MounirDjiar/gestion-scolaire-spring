package com.groupe2.gestionscolaire.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.groupe2.gestionscolaire.model.Clazz;
import com.groupe2.gestionscolaire.model.Schedule;
import com.groupe2.gestionscolaire.model.School;
import com.groupe2.gestionscolaire.model.Teacher;

public interface ScheduleDao extends JpaRepository <Schedule, Long>{
	
	public List<Schedule> findBySchoolId(Long id);
	
	public List<Schedule> findByClazzId(Long id);
	
	@Query("SELECT t FROM Teacher t JOIN t.schedules sc WHERE sc.id = :id")
	Teacher findTeacherByScheduleId(@Param("id") Long id);
	
	@Query("SELECT s FROM School s JOIN s.schedules sc WHERE sc.id = :id")
	School findSchoolByScheduleId(@Param("id") Long id);
	
	@Query("SELECT c FROM Clazz c JOIN c.schedules sc WHERE sc.id = :id")
	Clazz findClazzByScheduleId(@Param("id") Long id);
	
	

}
