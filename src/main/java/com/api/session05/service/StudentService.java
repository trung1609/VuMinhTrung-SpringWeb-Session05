package com.api.session05.service;

import com.api.session05.model.dto.request.student_request.StudentRequest;
import com.api.session05.model.dto.response.student_response.StudentResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getAllStudent();

    Page<StudentResponse> getAllStudent(int page, int size, String sortBy, String direction);

    StudentResponse findStudentById(Long id);

    StudentResponse createStudent(StudentRequest request);

    StudentResponse updateStudent(Long id, StudentRequest request);

    void deleteById(Long id);

    List<StudentResponse> findAllStudentByCourseTitle(String courseTitle);
}
