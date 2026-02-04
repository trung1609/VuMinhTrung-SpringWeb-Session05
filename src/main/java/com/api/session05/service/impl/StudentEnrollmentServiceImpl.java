package com.api.session05.service.impl;

import com.api.session05.mapper.StudentEnrollmentMapper;
import com.api.session05.model.dto.request.student_enrollment_request.SearchStudentByName;
import com.api.session05.model.dto.request.student_enrollment_request.StudentEnrollmentRequest;
import com.api.session05.model.dto.response.student_enrollment_response.StudentEnrollmentResponse;
import com.api.session05.model.entity.Course;
import com.api.session05.model.entity.CourseStatus;
import com.api.session05.model.entity.Student;
import com.api.session05.model.entity.StudentEnrollment;
import com.api.session05.repository.CourseRepository;
import com.api.session05.repository.StudentEnrollmentRepository;
import com.api.session05.repository.StudentRepository;
import com.api.session05.service.StudentEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentEnrollmentServiceImpl implements StudentEnrollmentService {

    @Autowired
    private StudentEnrollmentRepository studentEnrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentEnrollmentMapper studentEnrollmentMapper;

    @Override
    public void enrollStudent(StudentEnrollmentRequest request) {
        // Tìm course và student
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + request.getCourseId()));
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + request.getStudentId()));

        // Kiểm tra khóa học phải có status là active
        if (course.getStatus() != CourseStatus.active) {
            throw new RuntimeException("Cannot enroll: Course is not active");
        }

        // Kiểm tra sinh viên đã đăng ký khóa học này chưa
        if (studentEnrollmentRepository.existsByStudentIdAndCourseId(request.getStudentId(), request.getCourseId())) {
            throw new RuntimeException("Student has already enrolled in this course");
        }

        // Lưu enrollment
        studentEnrollmentRepository.save(
                StudentEnrollment.builder()
                        .student(student)
                        .course(course)
                        .build()
        );
    }

    @Override
    public List<StudentEnrollmentResponse> getAllStudentEnrollment() {
        List<StudentEnrollment> studentEnrollment = studentEnrollmentRepository.findAll();
        return studentEnrollment.stream()
                .map(studentEnrollmentMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public void deleteStudentInCourse(StudentEnrollmentRequest request) {
        // Kiểm tra course có tồn tại không
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + request.getCourseId()));

        // Kiểm tra student có tồn tại không
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + request.getStudentId()));

        // Kiểm tra enrollment có tồn tại không
        if (!studentEnrollmentRepository.existsByStudentIdAndCourseId(request.getStudentId(), request.getCourseId())) {
            throw new RuntimeException("Student is not enrolled in this course");
        }

        // Xóa enrollment
        studentEnrollmentRepository.deleteByStudentIdAndCourseId(request.getStudentId(), request.getCourseId());
    }

    @Override
    @Transactional
    public List<StudentEnrollmentResponse> searchStudentInCourse(SearchStudentByName request) {

        return studentEnrollmentRepository.findByStudentNameContainingIgnoreCaseAndCourseId(request.getStudentName(), request.getCourseId());
    }

    @Override
    public List<StudentEnrollmentResponse> findAllByCourseTitle(String courseTitle) {
        return studentEnrollmentRepository.findAllByCourseTitle(courseTitle);
    }
}
