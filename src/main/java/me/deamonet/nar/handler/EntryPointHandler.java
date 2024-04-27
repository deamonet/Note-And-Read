package me.deamonet.nar.handler;

import com.google.gson.Gson;
import me.deamonet.nar.transmit.GenericResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class EntryPointHandler implements AuthenticationEntryPoint {

    @Resource
    Gson gson;

    //未登录时返回给前端数据
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        GenericResponse<Object> genericResponse = GenericResponse.unauthorized(new Object());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(gson.toJson(genericResponse));
    }
}
