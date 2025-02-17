package org.example.taskmanagerforteam.controller;

import lombok.RequiredArgsConstructor;
import org.example.taskmanagerforteam.dto.TaskDto;
import org.example.taskmanagerforteam.model.User;
import org.example.taskmanagerforteam.service.TaskService;
import org.example.taskmanagerforteam.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TasksController {

    private final TaskService taskService;
    private final UserService userService;

    @GetMapping("/admin/tasks/list")
    String findAllTasks(Model model) {
        model.addAttribute("users", userService.findAllUser());
        model.addAttribute("tasks", taskService.findAllTask());
        model.addAttribute("task", new TaskDto());
        return "tasks_list";
    }

    @GetMapping("/user/tasks/list")
    String findAllTasksUser(Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("users", userService.findAllUser());
        model.addAttribute("tasks", taskService.findAllTaskUser(currentUser.getId()));
        model.addAttribute("task", new TaskDto());
        return "tasks_list";
    }

    @PostMapping("/admin/tasks/addTask")
    String addNewTask(@ModelAttribute TaskDto taskDto){
        User user = userService.findById(taskDto.getUser().getId());
        taskDto.setUser(user);
        taskService.save(taskDto);
        return "redirect:/admin/tasks/list";
    }

}
