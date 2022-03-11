package edu.fra.uas.service;

import edu.fra.uas.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService{
    public void save(User user);
    public Optional<User> findUserByUserName(String username);
}
