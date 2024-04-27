package me.deamonet.nar.handler;

import com.google.gson.Gson;
import me.deamonet.nar.entity.User;
import me.deamonet.nar.service.UserService;
import me.deamonet.nar.transmit.GenericResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    Gson gson;

    @Resource
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        User user = userService.loadUserByUsername(username);
        //登录成功时返回给前端的数据
        GenericResponse<Object> result = GenericResponse.success((Integer) user.getId());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(gson.toJson(result));
    }
}
