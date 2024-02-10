package com.example.complaint.service;

import com.example.complaint.controller.comment.request.CommentRequest;
import com.example.complaint.exception.UserException;
import com.example.complaint.model.entity.Comment;
import com.example.complaint.model.entity.Complaint;
import com.example.complaint.model.entity.User;
import com.example.complaint.repository.CommentRepository;
import com.example.complaint.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserService userService;

    @Mock
    private ComplaintService complaintService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddComment_ValidRequest_Success() {
        when(authentication.getName()).thenReturn("testUser");

        User user = new User();
        user.setId("1");
        when(userService.findByUserName("testUser")).thenReturn(user);

        Complaint complaint = new Complaint();
        complaint.setId("1");
        when(complaintService.getComplaint("1")).thenReturn(complaint);

        CommentRequest request = new CommentRequest();
        request.setComment("Test comment");

        assertDoesNotThrow(() -> commentService.addComment("1", request, authentication));
    }

    @Test
    void testAddComment_UserNotLogged_ThrowsException() {
        when(authentication.getName()).thenReturn(null);

        CommentRequest request = new CommentRequest();

        assertThrows(UserException.class, () -> commentService.addComment("1", request, authentication));

        verify(commentRepository, never()).save(any(Comment.class));
    }
}

