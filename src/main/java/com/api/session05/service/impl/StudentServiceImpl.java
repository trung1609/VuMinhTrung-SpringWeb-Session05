package com.api.session05.service.impl;


import com.api.session05.mapper.PageMapper;
import com.api.session05.mapper.StudentMapper;
import com.api.session05.model.dto.request.PageRequestDTO;
import com.api.session05.model.dto.request.student_request.StudentRequest;
import com.api.session05.model.dto.response.PageResponseDTO;
import com.api.session05.model.dto.response.student_response.StudentResponse;
import com.api.session05.model.entity.Student;
import com.api.session05.repository.StudentRepository;
import com.api.session05.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private PageMapper pageMapper;

    @Override
    public List<StudentResponse> getAllStudent() {
        List<Student> student = studentRepository.findAll();
        return student.stream()
                .map(studentMapper::toDTO)
                .toList();
    }

    @Override
    public PageResponseDTO<StudentResponse> getAllStudent(PageRequestDTO request) {
        Sort sort;
        if (request.getSortBy() == null || request.getSortBy().isBlank()) {
            sort = Sort.by("id");
        } else {
            sort = Sort.by(request.getSortBy());
        }

        if (request.getDirection() == null || request.getDirection().isBlank()) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }

        if (request.getPage() == null) {
            request.setPage(0);
        }
        if (request.getSize() == null) {
            request.setSize(5);
        }

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Page<StudentResponse> page = studentRepository.findAll(pageable).map(studentMapper::toDTO);
        return pageMapper.mapPageToDTO(page);
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

    @Override
    public PageResponseDTO<StudentResponse> searchStudentByName(String name, PageRequestDTO request) {
        Sort sort;

        if (request.getSortBy() == null || request.getSortBy().isBlank()){
            sort = Sort.unsorted();
        }else{
            sort = Sort.by(request.getSortBy());
        }

        if (request.getDirection() == null || request.getDirection().isBlank()){
            sort = Sort.unsorted();
        }else{
            sort = Sort.by(request.getDirection());
        }

        if(request.getPage() == null){
            request.setPage(0);
        }

        if(request.getSize() == null){
            request.setSize(5);
        }

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Page<StudentResponse> page = studentRepository.searchStudentByName(name, pageable);
        return pageMapper.mapPageToDTO(page);
    }


}
