package com.groupe2.gestionscolaire.model;

import java.util.List;

import com.groupe2.gestionscolaire.model.enums.SchoolType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String address;
	
	@Enumerated(EnumType.STRING)
	private SchoolType schoolType;
	
	private String phoneNumber;
	
	private String logo;
	
	@OneToMany
	private List<Classroom> classrooms;
	
	@OneToMany
	private List<Teacher> teachers;
	
	@OneToMany
	private List<Lesson> lessons;
	
	@OneToMany
	private List<Clazz> Clazzs;
}