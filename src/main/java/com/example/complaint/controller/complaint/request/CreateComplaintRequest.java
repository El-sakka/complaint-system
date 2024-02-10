package com.example.complaint.controller.complaint.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateComplaintRequest {

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title must be less than or equal to 100 characters")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 500, message = "Description must be less than or equal to 500 characters")
    private String description;
}
