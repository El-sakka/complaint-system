package com.example.complaint.controller.comment;

import com.example.complaint.controller.comment.request.CommentRequest;
import com.example.complaint.service.CommentService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/complaints")
@Api(tags = "Comment Management")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}/comment")
    @ApiOperation(value = "Add a comment to a complaint", notes = "Endpoint to add a comment to a specific complaint")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Comment added successfully"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<Void> addComment(
            @ApiParam(value = "ID of the complaint to add comment to", required = true)
            @PathVariable("id") String complaintId,
            @ApiParam(value = "Comment request body", required = true)
            @Valid @RequestBody CommentRequest request, Authentication authentication) {
        commentService.addComment(complaintId, request, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
