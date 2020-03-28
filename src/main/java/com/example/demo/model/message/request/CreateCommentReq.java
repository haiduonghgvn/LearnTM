package com.example.demo.model.message.request;

import lombok.Data;

@Data
public class CreateCommentReq {
    private int postID;

    private String content;
}
