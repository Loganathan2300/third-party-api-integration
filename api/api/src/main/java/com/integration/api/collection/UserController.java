package com.integration.api.collection;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integration.api.entity.User;
import com.integration.api.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
    private UserService userService;

	@GetMapping("/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

//    @PostMapping("/user")
//    public User createUser(@RequestBody User user) {
//        return userService.createUser(user);
//    }
    
    @PostMapping("/user/api")
    public User postUserToApi(@RequestBody User user) {
    	return userService.postUserToApi(user);
    }
    
    //all the data are save it 
    @PostMapping("/user/data")
    public void createAllUser() {
        userService.createAllUser();
    }

    @PutMapping("/user/{id}")
    public User UpdateUser(@PathVariable Long id, @RequestBody User user) {
        return this.userService.update(id, user);
    }
    
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
    	return this.userService.deletedata(id);
    }
    
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.fetchUserFromApi(id);
    }
}
