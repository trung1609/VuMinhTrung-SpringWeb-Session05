package com.api.session05.controller;

import com.api.session05.model.dto.request.course_request.CourseCreateRequest;
import com.api.session05.model.dto.request.course_request.CourseUpdateDTORequest;
import com.api.session05.model.dto.response.ApiResponse;
import com.api.session05.model.dto.response.course_response.CourseResponse;
import com.api.session05.model.entity.CourseStatus;
import com.api.session05.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CourseResponse>>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        try {
            Page<CourseResponse> coursePage = courseService.getAllCourse(page, size, sortBy, direction);
            return new ResponseEntity<>(
                    new ApiResponse<>("Get all courses successfully", true, coursePage),
                    HttpStatus.OK
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    new ApiResponse<>(e.getMessage(), false, null),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> getCourseById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(new ApiResponse<>("Get course successfully", true, courseService.getCourseById(id)), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>("Not found with ID: "+id, false, null), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponse>> createCourse(@RequestBody CourseCreateRequest request) {
        try {
            return new ResponseEntity<>(new ApiResponse<>("Create course successfully", true, courseService.createCourse(request)), HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), false, null), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> updateCourse(@PathVariable Long id, @RequestBody CourseUpdateDTORequest request){
        try {
            return new ResponseEntity<>(new ApiResponse<>("Update course successfully", true, courseService.updateCourse(id, request)), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), false, null), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteCourse(@PathVariable Long id){
        try {
            courseService.deleteById(id);
            return new ResponseEntity<>(new ApiResponse<>("Delete course successfully", true, null), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), false, null), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/instructor")
    public ResponseEntity<ApiResponse<List<CourseResponse>>> findCourseByInstructorName(@RequestParam String instructorName) {
        try {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            "Find courses by instructor name successfully",
                            true,
                            courseService.findCourseByInstructorName(instructorName)
                    ),
                    HttpStatus.OK
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            e.getMessage(),
                            false,
                            null
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/status")
    public ResponseEntity<ApiResponse<List<CourseResponse>>> findAllCourseByStatus(@RequestParam CourseStatus status) {
        return new ResponseEntity<>(
                new ApiResponse<>(
                        "Get course by status successfully",
                        true,
                        courseService.findAllByCourseStatus(status)
                ),
                HttpStatus.OK
        );
    }


}
