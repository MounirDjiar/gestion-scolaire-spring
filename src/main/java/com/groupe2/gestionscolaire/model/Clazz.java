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
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Clazz {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	private String name;
	
	@ManyToOne
    @JsonIgnore
    private School school;
	
	@ManyToOne
	@JsonIgnore
	private Teacher mainTeacher;
	
	@OneToMany(mappedBy = "clazz")
	@JsonIgnore
	private List<Schedule> schedules;
}
