package com.we.cvr.services.cvbuilder;

import com.we.cvr.models.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManagerService {
    @Autowired
    UserRepository userRepository;

    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    public User getUser(int id) {
        return userRepository.findById(id).get();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(int id, User user) {
        user.setUserID(id);
        userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public User login(User user) {

        User found = userRepository.findByEmail(user.getEmail());

        if (found == null) return found;

        if (found.getPassword().trim().equals(user.getPassword().trim())) return found;
        return null;
    }
}
