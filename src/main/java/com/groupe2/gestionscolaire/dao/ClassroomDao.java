package com.groupe2.gestionscolaire.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.groupe2.gestionscolaire.model.Classroom;

public interface ClassroomDao extends JpaRepository<Classroom, Long> {
    
}
