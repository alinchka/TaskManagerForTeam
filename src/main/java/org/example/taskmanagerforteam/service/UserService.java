package org.example.taskmanagerforteam.service;

import org.example.taskmanagerforteam.dto.UserDto;
import org.example.taskmanagerforteam.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void save(UserDto userDto);

    User findByUsername(String username);
    User getCurrentUser();

    List<User> findAllUser();

    List<User> findByName(String name);

    User findById(Long id);

    void updateUser(User user);

    void deleteUser(Long id);

    boolean isUsernameAvailable(String username);

    boolean isEmailAvailable(String email);

}