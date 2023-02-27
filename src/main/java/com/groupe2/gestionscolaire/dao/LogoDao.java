package com.groupe2.gestionscolaire.dao;

import java.io.File;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupe2.gestionscolaire.model.Logo;

public interface LogoDao extends JpaRepository<Logo, Long>{

	Optional<Logo> findByName(String name);
}
