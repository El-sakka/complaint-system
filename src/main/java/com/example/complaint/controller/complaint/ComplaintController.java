package com.example.complaint.controller.complaint;

import com.example.complaint.controller.complaint.request.CreateComplaintRequest;
import com.example.complaint.controller.complaint.response.ComplaintResponse;
import com.example.complaint.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/complaints")
@Api(tags = "Complaint Management")
public class ComplaintController {

    private final ComplaintService complaintService;


    @PostMapping
    @ApiOperation(value = "Create a new complaint", notes = "Endpoint to create a new complaint")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Complaint created successfully", response = ComplaintResponse.class),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<ComplaintResponse> createComplaint(
            @ApiParam(value = "Complaint creation request", required = true)
            @Valid @RequestBody CreateComplaintRequest request, Authentication authentication){
        ComplaintResponse complaintResponse = complaintService.createComplaint(request,authentication);
        return new ResponseEntity<>(complaintResponse, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "Get list of complaints", notes = "Endpoint to retrieve list of complaints")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of complaints retrieved successfully", response = ComplaintResponse.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<List<ComplaintResponse>> getComplaintList(Authentication authentication){
        List<ComplaintResponse> complaintResponseList =  complaintService.getComplaintListByUserName(authentication);
        return new ResponseEntity<>(complaintResponseList,HttpStatus.OK);
    }




}
