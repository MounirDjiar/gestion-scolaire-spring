package com.groupe2.gestionscolaire.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groupe2.gestionscolaire.model.enums.Day;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;


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
	
	@ManyToOne
    @JsonIgnore
    private School school;
	
	@ManyToOne
	@JsonIgnore
	private Teacher teacher;
	
	@ManyToOne
	@JsonIgnore
	private Clazz clazz;
	
	
}
