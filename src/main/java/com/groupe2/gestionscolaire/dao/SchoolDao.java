package com.groupe2.gestionscolaire.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.groupe2.gestionscolaire.model.Classroom;
import com.groupe2.gestionscolaire.model.Clazz;
import com.groupe2.gestionscolaire.model.Lesson;
import com.groupe2.gestionscolaire.model.Schedule;
import com.groupe2.gestionscolaire.model.School;
import com.groupe2.gestionscolaire.model.Teacher;

public interface SchoolDao extends JpaRepository<School, Long>{
	
	public List<Classroom> findClassroomsById(Long id);
	
	public List<Clazz> findClazzsById(Long id);
	
	public List<Lesson> findLessonsById(Long id);
	
	@Query("SELECT sc FROM Schedule sc JOIN sc.school s WHERE s.id = :id")
	public List<Schedule> findSchedulesBySchoolId(@Param("id") Long id);
	
	@Query("SELECT t FROM Teacher t JOIN t.school s WHERE s.id = :id")
	public List<Teacher> findTeachersBySchoolId(@Param("id") Long id);
}
