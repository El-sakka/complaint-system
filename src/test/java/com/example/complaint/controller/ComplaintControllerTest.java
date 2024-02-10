package com.example.complaint.controller;

import com.example.complaint.controller.complaint.ComplaintController;
import com.example.complaint.controller.complaint.request.CreateComplaintRequest;
import com.example.complaint.controller.complaint.response.ComplaintResponse;
import com.example.complaint.service.ComplaintService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ComplaintControllerTest {

    @Mock
    ComplaintService complaintService;

    @Mock
    Authentication authentication;

    @InjectMocks
    ComplaintController complaintController;

    @Test
    void createComplaint_ValidRequest_Success() {

        CreateComplaintRequest request = new CreateComplaintRequest();
        request.setTitle("complaint");
        request.setDescription("descirption xxxxxxxxxxxx");
        ComplaintResponse expectedResponse = ComplaintResponse.builder()
                .title("complaint")
                .description("descirption xxxxxxxxxxxx")
                .build();
        when(complaintService.createComplaint(request, authentication)).thenReturn(expectedResponse);

        ResponseEntity<ComplaintResponse> responseEntity = complaintController.createComplaint(request, authentication);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());


    }

    @Test
    void getComplaintList_ValidRequest_Success() {
        ComplaintResponse expectedResponse = ComplaintResponse.builder()
                .title("complaint")
                .description("descirption xxxxxxxxxxxx")
                .build();

        when(complaintService.getComplaintListByUserName(authentication)).thenReturn(Collections.singletonList(expectedResponse));

        ResponseEntity<List<ComplaintResponse>> responseEntity = complaintController.getComplaintList(authentication);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());

    }
}
