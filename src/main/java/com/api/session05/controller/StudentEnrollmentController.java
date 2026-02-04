package com.api.session05.controller;

import com.api.session05.model.dto.request.student_enrollment_request.SearchStudentByName;
import com.api.session05.model.dto.request.student_enrollment_request.StudentEnrollmentRequest;
import com.api.session05.model.dto.response.ApiResponse;
import com.api.session05.model.dto.response.student_enrollment_response.StudentEnrollmentResponse;
import com.api.session05.service.StudentEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
public class StudentEnrollmentController {

    @Autowired
    private StudentEnrollmentService studentEnrollmentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentEnrollmentResponse>>> getAllEnrollments() {
        try {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            "Get all data successfully",
                            true,
                            studentEnrollmentService.getAllStudentEnrollment()
                    )
                    , HttpStatus.OK
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

    @PostMapping
    public ResponseEntity<ApiResponse<?>> enrollCourse(@RequestBody StudentEnrollmentRequest request) {
        try {
            studentEnrollmentService.enrollStudent(request);
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            "Data enrolled successfully",
                            true,
                            null
                    ),
                    HttpStatus.CREATED
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            e.getMessage(),
                            false,
                            null
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/courses/{courseId}/students/{studentId}")
    public ResponseEntity<ApiResponse<?>> deleteStudentInCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        try {
            StudentEnrollmentRequest request = new StudentEnrollmentRequest();
            request.setCourseId(courseId);
            request.setStudentId(studentId);
            studentEnrollmentService.deleteStudentInCourse(request);
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            "Student removed from course successfully",
                            true,
                            null
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
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("/courses/{courseId}/enrollments/students")
    public ResponseEntity<ApiResponse<List<StudentEnrollmentResponse>>> searchStudentInCourse(@PathVariable Long courseId, @RequestParam String studentName) {
        try {
            SearchStudentByName request = new SearchStudentByName();
            request.setStudentName(studentName);
            request.setCourseId(courseId);
            List<StudentEnrollmentResponse> response = studentEnrollmentService.searchStudentInCourse(request);
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            "Search completed successfully",
                            true,
                            response
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
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("/courses")
    public ResponseEntity<ApiResponse<List<StudentEnrollmentResponse>>> findAllByCourseTitle(@RequestParam String courseTitle){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        "Find students by course title successfully",
                        true,
                        studentEnrollmentService.findAllByCourseTitle(courseTitle)
                ),
                HttpStatus.OK
        );
    }

}
