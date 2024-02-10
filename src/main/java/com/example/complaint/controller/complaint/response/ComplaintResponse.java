package com.example.complaint.controller.complaint.response;

import com.example.complaint.model.dto.CommentDto;
import com.example.complaint.model.entity.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ComplaintResponse {

    String id;
    String title;
    String description;
    List<CommentDto> comments;
}
