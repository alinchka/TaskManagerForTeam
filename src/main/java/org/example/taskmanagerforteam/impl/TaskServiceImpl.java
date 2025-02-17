package org.example.taskmanagerforteam.impl;

import lombok.RequiredArgsConstructor;
import org.example.taskmanagerforteam.dto.TaskDto;
import org.example.taskmanagerforteam.model.TaskStatus;
import org.example.taskmanagerforteam.model.Tasks;
import org.example.taskmanagerforteam.repository.TaskRepository;
import org.example.taskmanagerforteam.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepo;

    @Override
    public List<Tasks> findAllTask() {
        return taskRepo.findAll();
    }

    @Override
    public List<Tasks> findAllTaskUser(Long userId) {
        return taskRepo.findByAssignedTo_Id(userId);
    }

    @Override
    public void save(TaskDto taskDto) {
        Tasks task = new Tasks();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(TaskStatus.OPEN);
        task.setDeadline(taskDto.getDeadline());
        task.setAssignedTo(taskDto.getUser());
        task.setCreatedAt(LocalDateTime.now());
        taskRepo.save(task);

    }
}
