package com.groupe2.gestionscolaire.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groupe2.gestionscolaire.model.enums.Day;

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
@NoArgsConstructor
@Entity
public class Schedule {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Day day;
		
	private String dStart;
	
	private String dEnd;
	
	
	@OneToOne
	@JsonIgnore
	private Lesson lesson;
			
	@OneToOne
	@JsonIgnore
	private Classroom classroom;
						
	@OneToOne
	@JsonIgnore
	private Teacher teacher;
	
	@OneToOne
	@JsonIgnore
	private Clazz clazz;
	
}
