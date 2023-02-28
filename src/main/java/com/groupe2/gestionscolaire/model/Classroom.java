package com.groupe2.gestionscolaire.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private int capacity;
    
    
    @ManyToOne
    private School school;
        
    // A verifier
    @ManyToMany(cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("excludedClassrooms")
    private List<Lesson> excludedLessons;
    
        
    // A verifier
    //@ManyToMany(mappedBy = "classrooms")
    //@JsonIgnore
    //private List<Lesson> lessons;
       
    
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.MERGE)
	@JsonIgnore
	private List<Schedule> schedules;     
    
}