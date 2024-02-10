package com.example.complaint.service;

import com.example.complaint.controller.complaint.request.CreateComplaintRequest;
import com.example.complaint.controller.complaint.response.ComplaintResponse;
import com.example.complaint.exception.UserException;
import com.example.complaint.mapper.ComplaintMapper;
import com.example.complaint.model.entity.Complaint;
import com.example.complaint.model.entity.User;
import com.example.complaint.repository.ComplaintRepository;
import com.example.complaint.service.impl.ComplaintServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class ComplaintServiceImplTest {

    @Mock
    private ComplaintRepository complaintRepository;

    @Mock
    private UserService userService;

    @Mock
    private ComplaintMapper complaintMapper;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private ComplaintServiceImpl complaintService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateComplaint_ValidRequest_Success() {
        when(authentication.getName()).thenReturn("testUser");

        User user = new User();
        user.setId("1");
        when(userService.findByUserName("testUser")).thenReturn(user);

        when(complaintRepository.save(any(Complaint.class))).thenAnswer(invocation -> {
            Complaint savedComplaint = invocation.getArgument(0);
            savedComplaint.setId("1");
            return savedComplaint;
        });

        when(complaintMapper.toComplaintResponse(any(Complaint.class))).thenAnswer(invocation -> {
            Complaint complaint = invocation.getArgument(0);
            return ComplaintResponse.builder()
                    .id(complaint.getId())
                    .title(complaint.getTitle())
                    .description(complaint.getDescription())
                    .build();
        });

        CreateComplaintRequest request = new CreateComplaintRequest();
        request.setTitle("Test Complaint");
        request.setDescription("This is a test complaint");

        ComplaintResponse response = complaintService.createComplaint(request, authentication);

        assertNotNull(response);
        assertEquals("1", response.getId());
        assertEquals("Test Complaint", response.getTitle());
        assertEquals("This is a test complaint", response.getDescription());
    }

    @Test
    public void testCreateComplaint_UserNotFound_ThrowsException() {
        when(authentication.getName()).thenReturn("testUser");

        when(userService.findByUserName("testUser")).thenThrow(UserException.class);

        CreateComplaintRequest request = new CreateComplaintRequest();
        request.setTitle("Test Complaint");
        request.setDescription("This is a test complaint");

        assertThrows(UserException.class, () -> complaintService.createComplaint(request, authentication));
    }

}
