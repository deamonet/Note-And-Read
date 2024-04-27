package me.deamonet.nar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comment {
    private int cid;
    private String content;
    private String replyTo;
    private int articleId;
}