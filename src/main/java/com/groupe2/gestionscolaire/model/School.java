package com.groupe2.gestionscolaire.model;


import java.sql.Blob;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groupe2.gestionscolaire.model.enums.SchoolType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	
	@OneToOne
    private Logo  logo;
	
	
	@OneToMany(mappedBy = "school")
	@JsonIgnore
	private List<Classroom> classrooms;
					
	@OneToMany(mappedBy = "school")
	@JsonIgnore
	private List<Teacher> teachers;
		
	@OneToMany(mappedBy = "school")
	@JsonIgnore
	private List<Lesson> lessons;
	
	@OneToMany(mappedBy = "school")
	@JsonIgnore
	private List<Clazz> clazzs;
	
	@OneToMany(mappedBy = "school")
	@JsonIgnore
	private List<Schedule> schedules;
	
} 