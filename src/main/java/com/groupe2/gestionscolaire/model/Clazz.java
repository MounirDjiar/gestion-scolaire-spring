package com.groupe2.gestionscolaire.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Clazz {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String name;
	
	@NonNull
	@OneToOne(mappedBy = "mainClazz")
	private Teacher mainTeacher;
	
	
	@ManyToOne
	private School school;
	
	@ManyToMany
	private List<Schedule> schedules;
	
	@ManyToMany
	private List<Lesson> lessons;
	
}
