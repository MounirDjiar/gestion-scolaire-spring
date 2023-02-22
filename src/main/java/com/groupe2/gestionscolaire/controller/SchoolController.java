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

import com.groupe2.gestionscolaire.dao.SchoolDao;
import com.groupe2.gestionscolaire.model.School;

@RestController
@CrossOrigin
@RequestMapping("/schools")
public class SchoolController {

	@Autowired
	SchoolDao schoolDao;

	@GetMapping({ "", "/" })
	public ResponseEntity<List<School>> finAll() {

		return new ResponseEntity<List<School>>(schoolDao.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<School> findOne(@PathVariable Long id) {

		Optional<School> optionSchool = schoolDao.findById(id);

		return (optionSchool.isPresent()) ? new ResponseEntity<School>(optionSchool.get(), HttpStatus.OK)
				: new ResponseEntity<School>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOne(@PathVariable Long id) {

		try {
			schoolDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping({ "", "/" })
	public ResponseEntity<School> addOne(@RequestBody School school) {
		schoolDao.save(school);
		return new ResponseEntity<School>(school, HttpStatus.CREATED);
	}
}