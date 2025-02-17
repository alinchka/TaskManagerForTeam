package org.example.taskmanagerforteam.repository;

import org.example.taskmanagerforteam.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findByAssignedTo_Id(Long userId);
}

