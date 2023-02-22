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

import com.groupe2.gestionscolaire.dao.ScheduleDao;
import com.groupe2.gestionscolaire.model.Schedule;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
	
	@Autowired
	ScheduleDao scheduleDao;
	
	@GetMapping({"", "/"})
	public ResponseEntity<List<Schedule>> findAll(@RequestParam(defaultValue = "0") Integer init, Model model) {
		return new ResponseEntity<List<Schedule>>(scheduleDao.findAll(), HttpStatus.OK);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Schedule> findOne(@PathVariable long id) {
		Optional<Schedule> optionalSchedule = scheduleDao.findById(id);
		return optionalSchedule.isPresent()?
				new ResponseEntity<Schedule>(optionalSchedule.get(), HttpStatus.OK) :
				new ResponseEntity<Schedule>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOne(@PathVariable long id) {
		Optional<Schedule> optionalSchedule = scheduleDao.findById(id);
		if (optionalSchedule.isPresent()) {
			this.scheduleDao.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping({"","/"})
	public ResponseEntity<Schedule> addOne(@RequestBody Schedule Schedule) {
		this.scheduleDao.save(Schedule);
		return new ResponseEntity<Schedule>(Schedule, HttpStatus.CREATED);
	}
}
