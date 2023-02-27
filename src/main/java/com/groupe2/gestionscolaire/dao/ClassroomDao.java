package com.groupe2.gestionscolaire.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.groupe2.gestionscolaire.model.Classroom;
import com.groupe2.gestionscolaire.model.Lesson;
import com.groupe2.gestionscolaire.model.School;

public interface ClassroomDao extends JpaRepository<Classroom, Long> {
	
	public List<Classroom> findBySchoolId(Long id);
	
	@Query("SELECT s FROM School s JOIN s.classrooms c WHERE c.id = :id")
	School findSchoolByClassroomsId(@Param("id") Long id);

	@Query("SELECT c.excludedLessons FROM Classroom c WHERE c.id = :classroomId")
	List<Lesson> findExcludedLessonsByClassroomId(@Param("classroomId") Long classroomId);
	
	@Query("SELECT l FROM Lesson l JOIN l.classrooms c WHERE c.id = :classroomId AND l NOT IN (SELECT el FROM Classroom c JOIN c.excludedLessons el WHERE c.id = :classroomId)")
    List<Lesson> findNonExcludedLessonsByClassroomId(@Param("classroomId") Long classroomId);
}
