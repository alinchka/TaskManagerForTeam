package org.example.taskmanagerforteam.impl;

import lombok.RequiredArgsConstructor;
import org.example.taskmanagerforteam.dto.UserDto;
import org.example.taskmanagerforteam.model.Role;
import org.example.taskmanagerforteam.model.User;
import org.example.taskmanagerforteam.repository.UserRepository;
import org.example.taskmanagerforteam.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> findAllUser() {
        return userRepo.findAll();
    }

    @Override
    public void save(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setRoles(Collections.singleton(Role.USER));

        userRepo.save(user);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepo.findByName(name);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Пользователь с ID: " + id + " не найден!"));
    }

    @Override
    public void updateUser(User user) {
        User updateUser = userRepo.findById(user.getId()).orElse(null);
        if (updateUser == null) {
            throw new IllegalArgumentException("Пользователь не найден!");
        }
        updateUser.setName(user.getName());
        updateUser.setSurname(user.getSurname());
        updateUser.setUsername(user.getUsername());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        userRepo.save(updateUser);
    }
    @Override
    public void deleteUser(Long id) {
        this.userRepo.deleteById(id);
    }
    @Override
    public boolean isUsernameAvailable(String username) {
        return userRepo.existsByUsername(username);
    }
    @Override
    public boolean isEmailAvailable(String email) {
        return userRepo.existsByEmail(email);
    }
    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        return userRepo.findByUsername(username);
    }
}
