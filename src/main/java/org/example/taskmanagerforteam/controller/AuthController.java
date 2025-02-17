package org.example.taskmanagerforteam.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.taskmanagerforteam.dto.UserDto;
import org.example.taskmanagerforteam.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/registration")
    String registrationPage(@ModelAttribute("userDto") UserDto userDto) {
        return "registration";
    }

    @PostMapping("/registration")
    String saveUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (userServiceImpl.isUsernameAvailable(userDto.getUsername())) {
            bindingResult.rejectValue("username","error.username", "Имя пользователя уже занято!");
            return "registration";
        }

        if (userServiceImpl.isEmailAvailable(userDto.getEmail())) {
            bindingResult.rejectValue("email", "error.email","Почта уже занята!");
            return "registration";
        }

        userServiceImpl.save(userDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid credentials");
        }
        return "login";
    }



    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (invalidCredentials(username, password)) {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Возвращаем страницу с ошибкой, статус 200
        }
        return "redirect:/profile"; // В случае успешного логина редирект
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        // Логика логаута
        model.addAttribute("message", "Вы успешно вышли из системы.");
        return "login";  // Перенаправление на страницу логина с сообщением
    }
    private boolean invalidCredentials(String username, String password) {
        // Логика для проверки учетных данных
        // Например, можно использовать службу для аутентификации
        return username == null || password == null || !username.equals("correctUsername") || !password.equals("correctPassword");
    }

}
