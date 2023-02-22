package com.groupe2.gestionscolaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.groupe2.gestionscolaire.model.Classroom;
import com.groupe2.gestionscolaire.dao.ClassroomDao;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
@CrossOrigin
public class ClassroomController {

    @Autowired
    private ClassroomDao classroomDao;

    @GetMapping
    public List<Classroom> getAllClassrooms() {
        return classroomDao.findAll();
    }

    @GetMapping("/{id}")
    public Classroom getClassroomById(@PathVariable Long id) {
        return classroomDao.findById(id).orElse(null);
    }

    @PostMapping
    public Classroom createClassroom(@RequestBody Classroom classroom) {
        return classroomDao.save(classroom);
    }


    @DeleteMapping("/{id}")
    public void deleteClassroom(@PathVariable Long id) {
        classroomDao.deleteById(id);
    }

}
