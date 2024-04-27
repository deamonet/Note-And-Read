package me.deamonet.nar.entity;

import lombok.Data;

import java.util.Date;


@Data
public class NoteMaria extends Note{
    private Integer id;
    private String content;
    private String cover;
    private Date firstEdit;
    private Date lastEdit;
    private String category;
}
