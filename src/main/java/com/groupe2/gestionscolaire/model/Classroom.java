package com.groupe2.gestionscolaire.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    //@JsonIgnore
    private School school;
    
    @ManyToMany(mappedBy = "classrooms")
    @JsonIgnore
    private List<Lesson> lessons;
    
                  
    @ManyToMany(mappedBy = "excludedClassrooms")
    @JsonIgnore
    private List<Lesson> excludedLessons;
    
    @OneToOne(mappedBy = "classroom")
    @JsonIgnore
    private Schedule schedule;       
    
}