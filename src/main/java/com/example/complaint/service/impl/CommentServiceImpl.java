package com.example.complaint.service.impl;

import com.example.complaint.controller.comment.request.CommentRequest;
import com.example.complaint.exception.UserException;
import com.example.complaint.model.entity.Comment;
import com.example.complaint.model.entity.Complaint;
import com.example.complaint.model.entity.User;
import com.example.complaint.repository.CommentRepository;
import com.example.complaint.service.CommentService;
import com.example.complaint.service.ComplaintService;
import com.example.complaint.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ComplaintService complaintService;
    @Override
    public void addComment(String complaintId,CommentRequest request, Authentication authentication) {
        String userName = getUserName(authentication);
        User user = userService.findByUserName(userName);
        Complaint complaint = complaintService.getComplaint(complaintId);
        Comment comment = Comment.builder()
                .comment(request.getComment())
                .user(user)
                .complaint(complaint)
                .build();
        commentRepository.save(comment);
    }

    private String getUserName(Authentication authentication) {
        if (Optional.ofNullable(authentication).isPresent() && Optional.ofNullable(authentication.getName()).isPresent()) {
            return authentication.getName();
        } else {
            throw new UserException("User not logged.");
        }
    }
}
