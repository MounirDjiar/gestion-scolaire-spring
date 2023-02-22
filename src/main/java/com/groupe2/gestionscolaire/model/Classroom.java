package com.groupe2.gestionscolaire.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Lesson[] excludedLessons;
    private int capacity;
    
    
}
