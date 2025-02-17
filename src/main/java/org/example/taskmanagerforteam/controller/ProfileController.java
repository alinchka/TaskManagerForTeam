package org.example.taskmanagerforteam.controller;

import lombok.RequiredArgsConstructor;
import org.example.taskmanagerforteam.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    String profile(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("user", userService.findByUsername(user.getUsername()));
        return "profile";
    }
}
