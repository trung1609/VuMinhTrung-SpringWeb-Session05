package com.api.session05.service;


import com.api.session05.model.dto.request.student_enrollment_request.SearchStudentByName;
import com.api.session05.model.dto.request.student_enrollment_request.StudentEnrollmentRequest;
import com.api.session05.model.dto.response.student_enrollment_response.StudentEnrollmentResponse;

import java.util.List;

public interface StudentEnrollmentService {
    void enrollStudent(StudentEnrollmentRequest request);
    List<StudentEnrollmentResponse> getAllStudentEnrollment();

    void deleteStudentInCourse(StudentEnrollmentRequest request);

    List<StudentEnrollmentResponse> searchStudentInCourse(SearchStudentByName request);

    List<StudentEnrollmentResponse> findAllByCourseTitle(String courseTitle);
}
