package com.groupe2.gestionscolaire.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupe2.gestionscolaire.model.Lesson;

public interface LessonDao extends JpaRepository<Lesson, Long>{

	public List<Lesson> findBySchoolId(Long id);

	public List<Lesson> findByTeachersId(Long teacherId);
}
