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
import com.groupe2.gestionscolaire.model.Lesson;

@RestController
@CrossOrigin
@RequestMapping("/lessons")
public class LessonController {

	@Autowired
	LessonDao ldao;
	
	@GetMapping({"", "/"})
	public ResponseEntity<List<Lesson>> findAll(){
		return new ResponseEntity<List<Lesson>>(ldao.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Lesson> findOne(@PathVariable long id){
		Optional<Lesson> optionLesson = ldao.findById(id);
		
		return optionLesson.isPresent() ?
				new ResponseEntity<Lesson>(optionLesson.get(), HttpStatus.OK) :
				new ResponseEntity<Lesson>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOne(@PathVariable long id){
		this.ldao.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
}
	
	@PostMapping({"", "/"})
	public ResponseEntity<Lesson> addOne(@RequestBody Lesson lesson){
		this.ldao.save(lesson);
		return new ResponseEntity<Lesson>(lesson, HttpStatus.CREATED);
	}

	
}