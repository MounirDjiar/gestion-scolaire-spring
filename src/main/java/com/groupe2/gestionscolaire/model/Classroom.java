package com.groupe2.gestionscolaire.model;

import java.util.List;

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
    
    @ManyToMany
    private List<Lesson> excludedLessons;
    
    @ManyToMany
    private List<Lesson> lessons;
    
    private int capacity;
    
    @ManyToOne
    private School school;
    
    @ManyToMany
    private List<Schedule> schedules;
    
}