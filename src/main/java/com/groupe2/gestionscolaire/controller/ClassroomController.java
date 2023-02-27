package com.groupe2.gestionscolaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.groupe2.gestionscolaire.model.Classroom;
import com.groupe2.gestionscolaire.model.Lesson;
import com.groupe2.gestionscolaire.dao.ClassroomDao;
import com.groupe2.gestionscolaire.dao.LessonDao;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/classrooms")
@CrossOrigin
public class ClassroomController {

	@Autowired
	private ClassroomDao classroomDao;
	
	private LessonDao lessonDao;

	@GetMapping({ "", "/" })
	public ResponseEntity<List<Classroom>> findAll() {
		return new ResponseEntity<List<Classroom>>(classroomDao.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Classroom> findOne(@PathVariable Long id) {
		Optional<Classroom> optionalClassroom = classroomDao.findById(id);
		return optionalClassroom.isPresent() ? new ResponseEntity<Classroom>(optionalClassroom.get(), HttpStatus.OK)
				: new ResponseEntity<Classroom>(HttpStatus.NOT_FOUND);
	}

	@PostMapping({ "", "/" })
	public ResponseEntity<?> createClassroom(@RequestBody Classroom classroom) {
		
//		if(classroom.getExcludedLessons().isEmpty()) {
//			return new ResponseEntity<String>("no_excluded_lessons", HttpStatus.BAD_REQUEST);
//		} else {
 /*       
        if (classroom.getLessons() != null) {
            for (Lesson lesson : classroom.getLessons()) {
                lesson = lessonDao.findById(lesson.getId()).orElse(null);
                if (lesson != null) {
                    lesson.getClassrooms().add(classroom);
                }
            }
        }
        
        if (classroom.getExcludedLessons() != null) {
            for (Lesson lesson : classroom.getExcludedLessons()) {
                lesson = lessonDao.findById(lesson.getId()).orElse(null);
                if (lesson != null) {
                    lesson.getExcludedClassrooms().add(classroom);
                }
            }
        }
*/        
		this.classroomDao.save(classroom);
		return new ResponseEntity<Classroom>(classroom, HttpStatus.CREATED);
		}
//	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
		Optional<Classroom> optionalClassroom = classroomDao.findById(id);
		if (optionalClassroom.isPresent()) {
			this.classroomDao.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/excludedlessons")
	public ResponseEntity<List<Lesson>> findExcludedLessons(@PathVariable Long id, Model model) {
		List<Lesson> excludedLessons = classroomDao.findExcludedLessonsByClassroomId(id);
		return excludedLessons.isEmpty() ? new ResponseEntity<List<Lesson>>(excludedLessons, HttpStatus.OK)
				: new ResponseEntity<List<Lesson>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}/nonexcludedlessons")
	public ResponseEntity<List<Lesson>> findNonExcludedLessons(@PathVariable Long id, Model model) {
		List<Lesson> nonExcludedLessons = classroomDao.findNonExcludedLessonsByClassroomId(id);
		return nonExcludedLessons.isEmpty() ? new ResponseEntity<List<Lesson>>(nonExcludedLessons, HttpStatus.OK)
				: new ResponseEntity<List<Lesson>>(HttpStatus.NOT_FOUND);
	}

}
