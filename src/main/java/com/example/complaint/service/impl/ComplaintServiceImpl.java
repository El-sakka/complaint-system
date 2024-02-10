package com.example.complaint.service.impl;

import com.example.complaint.controller.complaint.request.CreateComplaintRequest;
import com.example.complaint.controller.complaint.response.ComplaintResponse;
import com.example.complaint.exception.UserException;
import com.example.complaint.mapper.ComplaintMapper;
import com.example.complaint.model.entity.Complaint;
import com.example.complaint.model.entity.User;
import com.example.complaint.repository.ComplaintRepository;
import com.example.complaint.service.ComplaintService;
import com.example.complaint.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserService userService;
    ComplaintMapper complaintMapper = Mappers.getMapper(ComplaintMapper.class);

    @Override
    public ComplaintResponse createComplaint(CreateComplaintRequest request, Authentication authentication) {
        String userName = getUserName(authentication);

        User user = userService.findByUserName(userName);


        Complaint complaint = Complaint.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .user(user)
                .build();

        complaint = complaintRepository.save(complaint);
        return ComplaintResponse.builder().id(complaint.getId())
                .title(complaint.getTitle())
                .description(complaint.getDescription()).build();
    }

    @Override
    public List<ComplaintResponse> getComplaintListByUserName(Authentication authentication) {
        String userName = getUserName(authentication);

        User user = userService.findByUserName(userName);

        List<Complaint> complaints =  complaintRepository.findAllByUserId(user.getId());
        return complaints.stream()
                .map(complaintMapper::toComplaintResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Complaint getComplaint(String complaintId) {
        return complaintRepository.findById(complaintId).orElseThrow();
    }


    private String getUserName(Authentication authentication) {
        if (Optional.ofNullable(authentication).isPresent() && Optional.ofNullable(authentication.getName()).isPresent()) {
            return authentication.getName();
        } else {
            throw new UserException("User not logged.");
        }
    }
}
