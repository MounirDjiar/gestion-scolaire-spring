package com.groupe2.gestionscolaire.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.groupe2.gestionscolaire.model.Clazz;
import com.groupe2.gestionscolaire.model.Lesson;
import com.groupe2.gestionscolaire.model.Schedule;
import com.groupe2.gestionscolaire.model.School;
import com.groupe2.gestionscolaire.model.Teacher;

public interface TeacherDao extends JpaRepository<Teacher, Long> {

	public List<Teacher> findBySchoolId(Long id);

	@Query("SELECT l FROM Lesson l JOIN l.teachers t WHERE t = :teacherId")
	List<Lesson> findTaughtLessonsByTeacherId(@Param("teacherId") Long teacherId);

	@Query("SELECT c FROM Clazz c WHERE c.mainTeacher.id = :teacherId")
	List<Clazz> findMainClazzByMainTeacherId(@Param("teacherId") Long teacherId);

	@Query("SELECT COUNT(c) FROM Clazz c WHERE c.mainTeacher.id = :teacherId")
	long countMainClazzsByMainTeacherId(@Param("teacherId") Long teacherId);

	@Query("SELECT s FROM Schedule s JOIN s.teacher t WHERE t.id = :id")
	List<Schedule> findSchedulesByTeachersId(@Param("id") Long id);

}
