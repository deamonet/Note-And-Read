package me.deamonet.nar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NoteAndReadApplication {
    public static void main(String[] args){
        new SpringApplication(NoteAndReadApplication.class).run(args);
    }
}
