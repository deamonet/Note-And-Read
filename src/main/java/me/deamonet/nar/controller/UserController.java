package me.deamonet.nar.controller;

import me.deamonet.nar.entity.User;
import me.deamonet.nar.service.UserService;
import me.deamonet.nar.transmit.PasswordEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/{id:\\d+}")
public class UserController {
    @Resource
    UserService service;

    @GetMapping()
    public User findUserById(@PathVariable("id") int id) {
        return service.findUserById(id);
    }

    @GetMapping("/username")
    public User findUserByUsername(@RequestParam("username") String username){
        return service.loadUserByUsername(username);
    }

    @PutMapping()
    public Integer editUser(@PathVariable("id") int id,
                            @RequestBody User user) {
        user.setId(id);
        service.edit(user);
        return 1;
    }

    @DeleteMapping()
    public Integer deleteUser(@PathVariable("id") int id) {
        return service.delete(id);
    }

    @PutMapping("/reset-password")
    public Integer resetPassword(@PathVariable("id") int id, @RequestBody PasswordEntity password){
        return service.resetPassword(id, password.getPassword());
    }
}