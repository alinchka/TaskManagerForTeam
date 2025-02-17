package org.example.taskmanagerforteam.service;

import org.example.taskmanagerforteam.dto.TaskDto;
import org.example.taskmanagerforteam.model.Tasks;

import java.util.List;

public interface TaskService {

    List<Tasks> findAllTask();
    List<Tasks> findAllTaskUser(Long userId);

    void save(TaskDto taskDto);

}