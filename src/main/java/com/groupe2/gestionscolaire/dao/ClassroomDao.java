package com.groupe2.gestionscolaire.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.groupe2.gestionscolaire.model.Classroom;

public interface ClassroomDao extends JpaRepository<Classroom, Long> {
    	
	public List<Classroom> findBySchoolId(Long id);
	
	@Query("SELECT c FROM Classroom c WHERE c.school = id")
    List<Classroom> findBySchoolIdAcceptedClassroomsForLessonId(@Param("id") Long schoolID, @Param("lesson") Long lessonID);
	
}
