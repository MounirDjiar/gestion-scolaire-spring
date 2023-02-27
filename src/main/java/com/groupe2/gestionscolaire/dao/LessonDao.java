package com.groupe2.gestionscolaire.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.groupe2.gestionscolaire.model.Classroom;
import com.groupe2.gestionscolaire.model.Lesson;
import com.groupe2.gestionscolaire.model.School;
import com.groupe2.gestionscolaire.model.Teacher;

public interface LessonDao extends JpaRepository<Lesson, Long>{

	public List<Lesson> findBySchoolId(Long id);
	
	@Query
	("SELECT l.excludedClassrooms FROM Lesson l WHERE l.id = :lessonId")
    List<Classroom> findExcludedClassroomsByLessonId(@Param("lessonId") Long lessonId);
	
	@Query("SELECT c FROM Classroom c WHERE :lessonId NOT MEMBER OF c.excludedLessons")
	List<Classroom> findNonExcludedClassroomsByLessonId(@Param("lessonId") Long lessonId);
	
	
	@Query("SELECT l.teachers FROM Lesson l WHERE l.id = :lessonId")
	List<Teacher> findTeachersByLessonId(@Param("lessonId") Long lessonId);
	
//	@Query("SELECT l.lessonContent FROM Lesson l WHERE l.id NOT IN (:ids)")
//	List<Lesson> findAuthourisedLessonsById(@Param("ids") List<Long> ids);
}
