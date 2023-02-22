package com.groupe2.gestionscolaire.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupe2.gestionscolaire.model.Lesson;

public interface LessonDao extends JpaRepository<Lesson, Long>{

}
