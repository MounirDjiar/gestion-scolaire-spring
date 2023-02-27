package com.groupe2.gestionscolaire.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nonnull
    private Long id;
    
    @Nonnull
    private String name;
    
    @Nonnull
    private int capacity;
    
    @Nonnull
    @ManyToOne
//    @JsonIgnore
    private School school;
    
////    @ManyToMany(mappedBy = "excludedClassrooms", cascade= {CascadeType.ALL})
    @ManyToMany
    @JsonIgnore
    private List<Lesson> lessons;
    
    @Nonnull
//    @ManyToMany(cascade= {CascadeType.ALL})
    @ManyToMany
//    @JsonIgnore
    private List<Lesson> excludedLessons;
    
    
    
    
}