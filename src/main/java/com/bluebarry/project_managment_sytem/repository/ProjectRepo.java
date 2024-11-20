package com.bluebarry.project_managment_sytem.repository;

import com.bluebarry.project_managment_sytem.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepo extends JpaRepository<Project,Long> {
    Optional<Project> findById(Long id);
}
