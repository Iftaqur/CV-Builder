package com.we.cvr.controllers.auth;

import com.we.cvr.models.auth.User;
import com.we.cvr.services.cvbuilder.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserApiController {

    @Autowired
    private UserManagerService userManagerService;

    @RequestMapping("/api/users")
    public List<User> getUsers(){
        return userManagerService.getUsers();
    }

    @RequestMapping("/api/user/{id}")
    public User getUser(@PathVariable int id){
        return userManagerService.getUser(id);
    }

    @RequestMapping(value = "/api/signup", method = RequestMethod.POST)
    public void register(@RequestBody User user){
        userManagerService.addUser(user);
    }

    @RequestMapping(value = "/api/user/{id}/edit", method = RequestMethod.PUT)
    public void editProfile(@RequestBody User user, @PathVariable int id){
        userManagerService.updateUser(id, user);
    }

    @RequestMapping(value = "/api/user/{id}/delete", method = RequestMethod.DELETE)
    public void deleteProfile(@PathVariable int id){
        userManagerService.deleteUser(id);
    }
}
