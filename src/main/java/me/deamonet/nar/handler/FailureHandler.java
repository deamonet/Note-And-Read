package me.deamonet.nar.handler;

import com.google.gson.Gson;
import me.deamonet.nar.transmit.GenericResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FailureHandler implements AuthenticationFailureHandler {

    @Resource
    Gson gson;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        GenericResponse<Object> genericResponse = new GenericResponse<>();
        if (e instanceof UsernameNotFoundException) {
            genericResponse = GenericResponse.usernameNotFound(e.getMessage());
        } else if (e instanceof BadCredentialsException) {
            genericResponse = GenericResponse.validateFailed(e.getMessage());
        } else {
            genericResponse = GenericResponse.failed(e.getMessage());
        }
        //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        //返回给前台
        response.getWriter().write(gson.toJson(genericResponse));
    }
}
