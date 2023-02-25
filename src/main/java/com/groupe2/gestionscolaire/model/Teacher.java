package com.groupe2.gestionscolaire.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class Teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String dob;
			
	@ManyToOne
	private School school;
	
	@ManyToMany
	@JsonIgnoreProperties("teachers")
	private List<Lesson> lessons;
	
	
	@OneToOne(mappedBy = "mainTeacher")
	@JsonIgnore
	private Clazz mainClazz;
	
	@OneToOne(mappedBy = "teacher")
	@JsonIgnore
	private Schedule schedule;				
}