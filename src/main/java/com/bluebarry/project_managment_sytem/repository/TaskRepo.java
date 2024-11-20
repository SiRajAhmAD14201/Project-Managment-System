package com.bluebarry.project_managment_sytem.repository;

import com.bluebarry.project_managment_sytem.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {


}
