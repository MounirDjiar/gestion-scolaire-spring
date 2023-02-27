package com.groupe2.gestionscolaire.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.groupe2.gestionscolaire.FileUploadUtil;
import com.groupe2.gestionscolaire.dao.ClassroomDao;
import com.groupe2.gestionscolaire.dao.ClazzDao;
import com.groupe2.gestionscolaire.dao.LessonDao;
import com.groupe2.gestionscolaire.dao.SchoolDao;
import com.groupe2.gestionscolaire.dao.TeacherDao;
import com.groupe2.gestionscolaire.model.Classroom;
import com.groupe2.gestionscolaire.model.Clazz;
import com.groupe2.gestionscolaire.model.Lesson;
import com.groupe2.gestionscolaire.model.School;
import com.groupe2.gestionscolaire.model.Teacher;

@RestController
@CrossOrigin
@RequestMapping("/schools")
public class SchoolController {

	@Autowired
	SchoolDao schoolDao;
	
	@Autowired
	ClassroomDao classroomDao;
	
	
	@Autowired
	TeacherDao teacherDao;
	
	@Autowired
	LessonDao lessonDao;
	
	@Autowired
	ClazzDao clazzDao;
	
	
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
	
	
	
	
//	@PostMapping({ "", "/" })
//	public ResponseEntity<School> addOne(@RequestBody School school, @RequestParam("logo") MultipartFile logo) throws IOException  {
//		
//		String fileName = StringUtils.cleanPath(logo.getOriginalFilename());
//		school.setLogo(fileName);
//		School savedSchool = schoolDao.save(school);
//		
//		String uploadDir = "user-photos/" + savedSchool.getId();
//        FileUploadUtil.saveFile(uploadDir, fileName, logo);
//        
//		schoolDao.save(school);
//		return new ResponseEntity<School>(school, HttpStatus.CREATED);
//	}
//	
	
//	@PostMapping({ "", "/" })
//	public ResponseEntity<School> addOne(@RequestParam("logo")MultipartFile logo, @RequestBody School school) {
//		school.setLogo(logo);	
//		schoolDao.save(school);
//		return new ResponseEntity<School>(school, HttpStatus.CREATED);
//	}
	
	
	
	@GetMapping("/{schoolID}/classrooms")	
	public ResponseEntity<List<Classroom>> findClassroomsBySchoolId(@PathVariable Long schoolID) {
		return new ResponseEntity<List<Classroom>>(classroomDao.findBySchoolId(schoolID), HttpStatus.OK); 					
	}
	
	@GetMapping("/{schoolID}/teachers")	
	public ResponseEntity<List<Teacher>> findTeachersBySchoolId(@PathVariable Long schoolID) {
		return new ResponseEntity<List<Teacher>>(teacherDao.findBySchoolId(schoolID), HttpStatus.OK); 					
	}
		
	@GetMapping("/{schoolID}/clazzs")	
	public ResponseEntity<List<Clazz>> findClazzsBySchoolId(@PathVariable Long schoolID) {
		return new ResponseEntity<List<Clazz>>(clazzDao.findBySchoolId(schoolID), HttpStatus.OK); 					
	}
	
	@GetMapping("/{schoolID}/lessons")	
	public ResponseEntity<List<Lesson>> findLessonsBySchoolId(@PathVariable Long schoolID) {
		return new ResponseEntity<List<Lesson>>(lessonDao.findBySchoolId(schoolID), HttpStatus.OK); 					
	}
	
}

