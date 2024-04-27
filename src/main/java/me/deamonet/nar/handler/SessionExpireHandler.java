package me.deamonet.nar.handler;

import com.google.gson.Gson;
import me.deamonet.nar.transmit.GenericResponse;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SessionExpireHandler implements SessionInformationExpiredStrategy {
    @Resource
    Gson gson;

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        GenericResponse<Object> result= GenericResponse.failed("您的账号在异地登录，建议修改密码");
        HttpServletResponse response=event.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(gson.toJson(result));
    }
}