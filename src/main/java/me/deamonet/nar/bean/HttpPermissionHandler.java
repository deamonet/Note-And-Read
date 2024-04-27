package me.deamonet.nar.bean;

import me.deamonet.nar.entity.User;
import me.deamonet.nar.exception.UserNotFoundException;
import me.deamonet.nar.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HttpPermissionHandler {
    final
    UserService userService;

    public HttpPermissionHandler(UserService userService) {
        this.userService = userService;
    }

    public boolean checkUserId(Authentication authentication, int userId){
        User user = userService.findUserById(userId);
        if (Objects.isNull(user)){
            throw new UserNotFoundException();
        }
        String username = authentication.getName();
        return user.getUsername().equals(username);
    }
}
