package com.api.session05.service.impl;


import com.api.session05.mapper.InstructorMapper;
import com.api.session05.model.dto.request.instructor_request.InstructorCreateRequest;
import com.api.session05.model.dto.request.instructor_request.InstructorUpdateDTORequest;
import com.api.session05.model.dto.response.instructor_response.InstructorResponse;
import com.api.session05.model.entity.Instructor;
import com.api.session05.repository.InstructorRepository;
import com.api.session05.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private InstructorMapper instructorMapper;

    @Override
    public InstructorResponse findInstructorById(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + id));
        return instructorMapper.toDto(instructor);
    }

    @Override
    public List<InstructorResponse> findAllInstructors() {
        List<Instructor> instructors = instructorRepository.findAll();
        return instructors.stream()
                .filter(instructor -> instructor.getIsDeleted() == false)
                .map(instructorMapper::toDto)
                .toList();
    }

    @Override
    public InstructorResponse createInstructor(InstructorCreateRequest request) {
        Instructor instructor = instructorMapper.toEntity(request);
        return instructorMapper.toDto(instructorRepository.save(instructor));
    }

    @Override
    public InstructorResponse updateInstructor(Long id, InstructorUpdateDTORequest request) {
        Instructor existingInstructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + id));
        instructorMapper.updateEntity(existingInstructor, request);
        return instructorMapper.toDto(instructorRepository.save(existingInstructor));
    }

    @Override
    public void deleteById(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + id));
        instructor.setIsDeleted(true);
        instructorRepository.save(instructor);
    }
}
