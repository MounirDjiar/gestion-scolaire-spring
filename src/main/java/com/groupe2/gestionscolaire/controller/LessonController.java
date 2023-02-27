package com.groupe2.gestionscolaire.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupe2.gestionscolaire.dao.LessonDao;
import com.groupe2.gestionscolaire.model.Classroom;
import com.groupe2.gestionscolaire.model.Lesson;
import com.groupe2.gestionscolaire.model.Teacher;

@RestController
@CrossOrigin
@RequestMapping("/lessons")
public class LessonController {

	@Autowired
	LessonDao ldao;

	@GetMapping({ "", "/" })
	public ResponseEntity<List<Lesson>> findAll() {
		return new ResponseEntity<List<Lesson>>(ldao.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Lesson> findOne(@PathVariable long id) {
		Optional<Lesson> optionLesson = ldao.findById(id);

		return optionLesson.isPresent() ? new ResponseEntity<Lesson>(optionLesson.get(), HttpStatus.OK)
				: new ResponseEntity<Lesson>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOne(@PathVariable long id) {
		this.ldao.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping({ "", "/" })
	public ResponseEntity<Lesson> addOne(@RequestBody Lesson lesson) {
		this.ldao.save(lesson);
		return new ResponseEntity<Lesson>(lesson, HttpStatus.CREATED);
	}

	@GetMapping("/{id}/excludedclassrooms")
	public ResponseEntity<List<Classroom>> findExcludedClassrooms(@PathVariable Long id) {
		List<Classroom> excludedClassroom = ldao.findExcludedClassroomsByLessonId(id);
		return excludedClassroom.isEmpty() ? new ResponseEntity<List<Classroom>>(excludedClassroom, HttpStatus.OK)
				: new ResponseEntity<List<Classroom>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}/nonexcludedclassrooms")
	public ResponseEntity<List<Classroom>> findNonExcludedClassrooms(@PathVariable Long id) {
		List<Classroom> nonExcludedClassroom = ldao.findNonExcludedClassroomsByLessonId(id);
		return nonExcludedClassroom.isEmpty() ? new ResponseEntity<List<Classroom>>(nonExcludedClassroom, HttpStatus.OK)
				: new ResponseEntity<List<Classroom>>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}/teachers")
	public ResponseEntity<List<Teacher>> findTeachersByLessonId(@PathVariable Long id) {
		List<Teacher> teacher = ldao.findTeachersByLessonId(id);
		return teacher.isEmpty() ? new ResponseEntity<List<Teacher>>(teacher, HttpStatus.OK)
				: new ResponseEntity<List<Teacher>>(HttpStatus.NOT_FOUND);
	}
	
//	@GetMapping("/lessonsbyid")
//	public ResponseEntity<List<Lesson>> findLessonsById(@PathVariable List<Long> ids) {
//		List<Lesson> lessons = ldao.findAuthourisedLessonsById(ids);
//		return lessons.isEmpty() ? new ResponseEntity<List<Lesson>>(lessons, HttpStatus.OK)
//				: new ResponseEntity<List<Lesson>>(HttpStatus.NOT_FOUND);
//	}
}