package com.example.complaint.mapper;

import com.example.complaint.controller.complaint.response.ComplaintResponse;
import com.example.complaint.model.entity.Complaint;
import org.mapstruct.Mapper;

@Mapper
public interface ComplaintMapper {

    ComplaintResponse toComplaintResponse(Complaint complaint);

}
