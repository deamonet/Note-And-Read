package me.deamonet.nar.constant;


import org.springframework.stereotype.Component;

@Component
public class UserPromptMessage {
    private static final String MESSAGE_PREFIX = "更新成功率";

    public String updateSuccessRate(Float rate) {
        return UserPromptMessage.MESSAGE_PREFIX + rate.toString();
    }
}
