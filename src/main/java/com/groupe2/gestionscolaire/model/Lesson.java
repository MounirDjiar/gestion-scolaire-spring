package com.groupe2.gestionscolaire.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Lesson {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String color;
	
	@ManyToOne
	private School school;
	
	@ManyToMany
	private List<Classroom> excludedClassrooms;
	
	@ManyToMany
	private List<Classroom> classrooms;
	
	@ManyToMany
	private List<Teacher> teachers;
		
	@ManyToMany
	private List<Clazz> clazzs;
		
	@ManyToMany
	private List<Schedule> schedules;
	
}
