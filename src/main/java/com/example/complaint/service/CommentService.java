package com.example.complaint.service;

import com.example.complaint.controller.comment.request.CommentRequest;
import org.springframework.security.core.Authentication;

public interface CommentService {
    void addComment(String complaintId,CommentRequest request, Authentication authentication);
}
