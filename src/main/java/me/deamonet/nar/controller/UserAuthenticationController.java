package me.deamonet.nar.controller;

import lombok.extern.slf4j.Slf4j;
import me.deamonet.nar.entity.User;
import me.deamonet.nar.exception.UsernameOccupiedException;
import me.deamonet.nar.service.UserService;
import me.deamonet.nar.transmit.UserRegistered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
public class UserAuthenticationController {
    final
    UserService service;
    final
    BCryptPasswordEncoder encoder;

    public UserAuthenticationController(UserService service, BCryptPasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }


    @PostMapping("/register")
    public Integer register(@RequestBody UserRegistered user) {
        User existUser = service.loadUserByUsername(user.getUsername());
        if (!Objects.isNull(existUser)){
            throw new UsernameOccupiedException();
        }
        log.info(user.toString());
        return service.register(new User(user.getName(), user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getPassword()));
    }
}
