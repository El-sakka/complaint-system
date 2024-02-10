package com.example.complaint.controller.comment.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentRequest {

    @NotEmpty(message = "Comment cannot be empty")
    private String comment;
}
