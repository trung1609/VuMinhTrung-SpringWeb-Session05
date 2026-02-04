package com.api.session05.controller;

import com.api.session05.model.dto.request.instructor_request.InstructorCreateRequest;
import com.api.session05.model.dto.request.instructor_request.InstructorUpdateDTORequest;
import com.api.session05.model.dto.response.ApiResponse;
import com.api.session05.model.dto.response.instructor_response.InstructorResponse;
import com.api.session05.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<InstructorResponse>>> getAllInstructors() {
        try {
            return new ResponseEntity<>(new ApiResponse<>("Get all instructors successfully",
                    true,
                    instructorService.findAllInstructors()), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    new ApiResponse<>(e.getMessage(), false, null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InstructorResponse>> getInstructorById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(new ApiResponse<>("Get instructor successfully",
                    true,
                    instructorService.findInstructorById(id)), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    new ApiResponse<>(e.getMessage(), false, null), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<InstructorResponse>> createInstructor(@RequestBody InstructorCreateRequest request) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(
                    "Create instructor successfully",
                    true,
                    instructorService.createInstructor(request)), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    new ApiResponse<>(e.getMessage(), false, null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InstructorResponse>> updateInstructor(@PathVariable Long id, @RequestBody InstructorUpdateDTORequest request) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(
                    "Update instructor successfully",
                    true,
                    instructorService.updateInstructor(id, request)), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    new ApiResponse<>(e.getMessage(), false, null), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteInstructor(@PathVariable Long id) {
        try {
            instructorService.deleteById(id);
            return new ResponseEntity<>(new ApiResponse<>("Delete instructor successfully", true, null), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), false, null), HttpStatus.NOT_FOUND);
        }
    }
}
