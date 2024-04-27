package me.deamonet.nar.bean;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "page")
@PropertySource("classpath:config.properties")
@Data
public class PageSize {
    private int size;
}
