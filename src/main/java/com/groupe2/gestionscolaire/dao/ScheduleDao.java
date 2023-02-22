package com.groupe2.gestionscolaire.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupe2.gestionscolaire.model.Schedule;

public interface ScheduleDao extends JpaRepository <Schedule, Long>{

}
