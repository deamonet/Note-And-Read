package me.deamonet.nar.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Notebook {
    // note folder
    private Integer id;
    private Integer uid;
    private String name;
    private String description;
    private Date createAt;
}
