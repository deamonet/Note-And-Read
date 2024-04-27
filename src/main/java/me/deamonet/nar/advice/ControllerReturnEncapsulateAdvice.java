package me.deamonet.nar.advice;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import me.deamonet.nar.constant.UserPromptMessage;
import me.deamonet.nar.transmit.GenericResponse;
import me.deamonet.nar.transmit.NoteNet;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 统一响应处理器
 * 在每个responseBody的响应返回之前进行处理
 **/
@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class ControllerReturnEncapsulateAdvice implements ResponseBodyAdvice<Object> {

    final
    UserPromptMessage userPromptMessage;
    @Resource
    Gson gson;

    public ControllerReturnEncapsulateAdvice(UserPromptMessage userPromptMessage) {
        this.userPromptMessage = userPromptMessage;
    }

    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (Objects.isNull(body)) {
            return GenericResponse.failed();
        }
        if (body instanceof Integer){
            return GenericResponse.success(body);
        }
        //String类型需要特殊处理 手动转为json字符串
        if (body instanceof String) {
            return gson.toJson(GenericResponse.success(body));
        }
        if (body instanceof GenericResponse) {
            return body;
        }
        //boolean类型 返回对应的成功或失败
        if (body instanceof Boolean) {
            return GenericResponse.builder((Boolean) body);
        }
        if (body instanceof Float) {
            return GenericResponse.success(body, userPromptMessage.updateSuccessRate((Float) body));
        }
        if (body instanceof NoteNet){
            return body;
        }
        return GenericResponse.success(body);
    }

}
