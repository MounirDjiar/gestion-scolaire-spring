package com.groupe2.gestionscolaire.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	//@JsonIgnore
	private School school;
	
	
	@ManyToMany
	@JsonIgnore
	private List<Classroom> classrooms;
		
	
	@ManyToMany
	@JsonIgnore
	private List<Classroom> excludedClassrooms;
		
	@ManyToMany
	@JsonIgnore
	private List<Teacher> teachers;
		
	@ManyToMany
	@JsonIgnore
	private List<Clazz> clazzs;
	
								
	@OneToOne(mappedBy = "lesson")
	@JsonIgnore
	private Schedule schedule;			
}
