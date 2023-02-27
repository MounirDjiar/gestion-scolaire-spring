package com.groupe2.gestionscolaire.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.groupe2.gestionscolaire.model.Classroom;
import com.groupe2.gestionscolaire.model.Clazz;
import com.groupe2.gestionscolaire.model.Schedule;
import com.groupe2.gestionscolaire.model.School;
import com.groupe2.gestionscolaire.model.Teacher;

public interface ClazzDao extends JpaRepository <Clazz, Long> {

	public List<Clazz> findBySchoolId(Long id);
	
	@Query("SELECT s FROM School s JOIN s.clazzs c WHERE c.id = :id")
	School findSchoolByClazzsId(@Param("id") Long id);
	
	@Query("SELECT c.mainTeacher FROM Clazz c WHERE c.id = :classId")
	Teacher findMainTeacherByClassId(@Param("classId") Long classId);
	
	@Query("SELECT s FROM Schedule s WHERE s.clazz.id = :classId")
    List<Schedule> findSchedulesByClazzId(@Param("classId") Long clazzId);
}
