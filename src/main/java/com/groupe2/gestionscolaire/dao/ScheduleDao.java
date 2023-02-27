package com.groupe2.gestionscolaire.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.groupe2.gestionscolaire.model.Schedule;


public interface ScheduleDao extends JpaRepository <Schedule, Long>{
	
	public List<Schedule> findBySchoolIdAndClazzId(Long schoolID, Long clazzID);
	
	public List<Schedule> findBySchoolIdAndTeacherId(Long schoolID, Long teacherID);	
	
}