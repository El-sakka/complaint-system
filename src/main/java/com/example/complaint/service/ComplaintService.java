package com.example.complaint.service;

import com.example.complaint.controller.complaint.request.CreateComplaintRequest;
import com.example.complaint.controller.complaint.response.ComplaintResponse;
import com.example.complaint.model.entity.Complaint;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ComplaintService {
    ComplaintResponse createComplaint(CreateComplaintRequest request, Authentication authentication);

    List<ComplaintResponse> getComplaintListByUserName(Authentication authentication);

    Complaint getComplaint(String complaintId);
}
