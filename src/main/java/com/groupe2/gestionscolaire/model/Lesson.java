package com.groupe2.gestionscolaire.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
			
	@ManyToMany(mappedBy = "excludedLessons", cascade = CascadeType.MERGE)
	@JsonIgnore
	private List<Classroom> excludedClassrooms;
	
		
	@ManyToMany(mappedBy = "lessons", cascade = CascadeType.MERGE)
	@JsonIgnore
	private List<Teacher> teachers;
	
			
	
	// A VERIFIER
	//@ManyToMany
	//@JsonIgnore
	//private List<Classroom> classrooms;
	
	
	// A VERIFIER
	//@ManyToMany
	//@JsonIgnore
	//private List<Clazz> clazzs;
	
								
	@OneToMany(mappedBy = "lesson", cascade = CascadeType.MERGE)
	@JsonIgnore
	private List<Schedule> schedule;
}
