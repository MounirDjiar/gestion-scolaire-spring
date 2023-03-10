package com.groupe2.gestionscolaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.groupe2.gestionscolaire.model.Classroom;
import com.groupe2.gestionscolaire.model.Lesson;
import com.groupe2.gestionscolaire.dao.ClassroomDao;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
@CrossOrigin
public class ClassroomController {

    @Autowired
    private ClassroomDao classroomDao;

    @GetMapping
    public ResponseEntity<List<Classroom>> getAllClassrooms() {
    	
    	return new ResponseEntity<List<Classroom>>(classroomDao.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public Classroom getClassroomById(@PathVariable Long id) {
        return classroomDao.findById(id).orElse(null);
    }
    
    @PostMapping({"", "/"})
	public ResponseEntity<Classroom> createClassroom(@RequestBody Classroom classroom){
		this.classroomDao.save(classroom);
		return new ResponseEntity<Classroom>(classroom, HttpStatus.CREATED);
	}
    
    @DeleteMapping("/{id}")
    public void deleteClassroom(@PathVariable Long id) {
        classroomDao.deleteById(id);
    }                

}
