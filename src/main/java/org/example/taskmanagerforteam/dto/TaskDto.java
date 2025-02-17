package org.example.taskmanagerforteam.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.taskmanagerforteam.model.TaskStatus;
import org.example.taskmanagerforteam.model.User;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDto {
    private String title;
    private String description;
    private LocalDateTime deadline;
    private TaskStatus status;
    private User user;
}

