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

import com.groupe2.gestionscolaire.dao.TeacherDao;
import com.groupe2.gestionscolaire.model.Teacher;

@RestController
@CrossOrigin
@RequestMapping("/teachers")
public class TeacherController {

	@Autowired
	TeacherDao tdao;
	
	@GetMapping({"", "/"})
	public ResponseEntity<List<Teacher>> findAll(){
		return new ResponseEntity<List<Teacher>>(tdao.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Teacher> findOne(@PathVariable long id){
		Optional<Teacher> optionTeacher = tdao.findById(id);
		
		return optionTeacher.isPresent() ?
				new ResponseEntity<Teacher>(optionTeacher.get(), HttpStatus.OK) :
				new ResponseEntity<Teacher>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOne(@PathVariable long id){
		this.tdao.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
}
	
	@PostMapping({"", "/"})
	public ResponseEntity<Teacher> addOne(@RequestBody Teacher teacher){
		this.tdao.save(teacher);
		return new ResponseEntity<Teacher>(teacher, HttpStatus.CREATED);
	}

	
}