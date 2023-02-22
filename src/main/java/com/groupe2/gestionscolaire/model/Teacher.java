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
public class Teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String dob;
			
	@ManyToOne
	@JsonIgnore
	private School school;
	
	@ManyToMany(mappedBy = "teachers")
	@JsonIgnore
	private List<Lesson> lessons;
	
	
	@OneToOne(mappedBy = "mainTeacher")
	@JsonIgnore
	private Clazz mainClazz;
	
	@OneToOne(mappedBy = "teacher")
	@JsonIgnore
	private Schedule schedule;				
}