package com.example.complaint.controller;
import com.example.complaint.controller.comment.CommentController;
import com.example.complaint.controller.comment.request.CommentRequest;
import com.example.complaint.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import javax.validation.Valid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest {

    @Mock
    CommentService commentService;

    @Mock
    Authentication authentication;

    @InjectMocks
    CommentController commentController;

    @Test
    void addComment_ValidRequest_Success() {
        String complaintId = "testComplaintId";
        CommentRequest request = new CommentRequest();

        ResponseEntity<Void> responseEntity = commentController.addComment(complaintId, request, authentication);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}
