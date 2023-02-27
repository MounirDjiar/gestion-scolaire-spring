package com.groupe2.gestionscolaire.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groupe2.gestionscolaire.dao.ClazzDao;
import com.groupe2.gestionscolaire.dao.TeacherDao;
import com.groupe2.gestionscolaire.model.Clazz;
import com.groupe2.gestionscolaire.model.Lesson;
import com.groupe2.gestionscolaire.model.Schedule;
import com.groupe2.gestionscolaire.model.Teacher;

@RestController
@RequestMapping("/clazzs")
public class ClazzController {

	@Autowired
	ClazzDao clazzDao;

	@Autowired
	TeacherDao teacherDao;

	@GetMapping({ "", "/" })
	public ResponseEntity<List<Clazz>> findAll(@RequestParam(defaultValue = "0") Integer init, Model model) {
		return new ResponseEntity<List<Clazz>>(clazzDao.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Clazz> findOne(@PathVariable long id) {
		Optional<Clazz> optionalClazz = clazzDao.findById(id);
		return optionalClazz.isPresent() ? new ResponseEntity<Clazz>(optionalClazz.get(), HttpStatus.OK)
				: new ResponseEntity<Clazz>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOne(@PathVariable long id) {
		Optional<Clazz> optionalClazz = clazzDao.findById(id);
		if (optionalClazz.isPresent()) {
			this.clazzDao.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping({ "", "/" })
	public ResponseEntity<Clazz> addOne(@RequestBody Clazz Clazz) {
		this.clazzDao.save(Clazz);
		return new ResponseEntity<Clazz>(Clazz, HttpStatus.CREATED);
	}

	@GetMapping("/{id}/mainteacher")
	public ResponseEntity<Teacher> findMainTeacher(@PathVariable Long id) {
		Teacher mainTeacher = clazzDao.findMainTeacherByClassId(id);
		if (mainTeacher.equals(null)) {
			return new ResponseEntity<Teacher>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Teacher>(mainTeacher, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}/schedules")
	public ResponseEntity<List<Schedule>> findSchedules(@PathVariable Long id) {
		List<Schedule> schedule = clazzDao.findSchedulesByClazzId(id);
		return schedule.isEmpty() ? new ResponseEntity<List<Schedule>>(schedule, HttpStatus.OK)
				: new ResponseEntity<List<Schedule>>(HttpStatus.NOT_FOUND);
	}
}
