package com.api.session05.service.impl;


import com.api.session05.mapper.StudentMapper;
import com.api.session05.model.dto.request.student_request.StudentRequest;
import com.api.session05.model.dto.response.student_response.StudentResponse;
import com.api.session05.model.entity.Student;
import com.api.session05.repository.StudentRepository;
import com.api.session05.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<StudentResponse> getAllStudent() {
        List<Student> student = studentRepository.findAll();
        return student.stream()
                .map(studentMapper::toDTO)
                .toList();
    }

    @Override
    public StudentResponse findStudentById(Long id) {
        return studentMapper.toDTO(
                studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id))
        );
    }

    @Override
    public StudentResponse createStudent(StudentRequest request) {
        Student student = studentMapper.toEntity(request);
        return studentMapper.toDTO(studentRepository.save(student));
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest request) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        studentMapper.updateEntity(existingStudent, request);
        return studentMapper.toDTO(studentRepository.save(existingStudent));
    }

    @Override
    public void deleteById(Long id) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentResponse> findAllStudentByCourseTitle(String courseTitle) {
        return studentRepository.findAllStudentByCourseTitle(courseTitle);
    }
}
