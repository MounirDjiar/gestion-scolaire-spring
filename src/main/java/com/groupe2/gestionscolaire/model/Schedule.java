package com.groupe2.gestionscolaire.model;

import java.util.List;

import com.groupe2.gestionscolaire.model.enums.Day;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Schedule {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private Day day;
	
	@NonNull
	private String dStart;
	
	@NonNull
	private String dEnd;
	
	@NonNull
	private Teacher teacher;
	
	@ManyToMany
	private List<Classroom> classrooms;
	
	@ManyToMany
	private List<Lesson> lessons;
	
}
