package com.api.session05.controller;

import com.api.session05.model.dto.request.student_request.StudentRequest;
import com.api.session05.model.dto.response.ApiResponse;
import com.api.session05.model.dto.response.student_response.StudentResponse;
import com.api.session05.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<StudentResponse>>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        try {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            "Get all students successfully",
                            true,
                            studentService.getAllStudent(page, size, sortBy, direction)),
                    HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), false, null), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudentById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(
                    new ApiResponse<>("Get student by id successfully", true, studentService.findStudentById(id)),
                    HttpStatus.OK
            );
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), false, null), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponse>> createStudent(@RequestBody StudentRequest request){
        try {
            return new ResponseEntity<>(new ApiResponse<>("Create student successfully", true, studentService.createStudent(request)), HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), false, null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> updateStudent(@PathVariable Long id, @RequestBody StudentRequest request){
        try {
            return new ResponseEntity<>(new ApiResponse<>("Update student successfully", true, studentService.updateStudent(id, request)), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), false, null), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteStudent(@PathVariable Long id){
        try {
            studentService.deleteById(id);
            return new ResponseEntity<>(new ApiResponse<>("Delete student successfully", true, null), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), false, null), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/courses")
    public ResponseEntity<ApiResponse<List<StudentResponse>>> findAllStudentByCourseTitle(@RequestParam String courseTitle){
        return new ResponseEntity<>(new ApiResponse<>("Find students by course title successfully", true, studentService.findAllStudentByCourseTitle(courseTitle)), HttpStatus.OK);
    }
}
