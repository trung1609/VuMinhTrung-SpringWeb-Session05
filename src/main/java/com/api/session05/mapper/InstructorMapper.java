package com.api.session05.mapper;

import com.api.session05.model.dto.request.instructor_request.InstructorCreateRequest;
import com.api.session05.model.dto.request.instructor_request.InstructorUpdateDTORequest;
import com.api.session05.model.dto.response.instructor_response.InstructorCourseResponse;
import com.api.session05.model.dto.response.instructor_response.InstructorResponse;
import com.api.session05.model.entity.Instructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class InstructorMapper {

    @Autowired
    private ModelMapper modelMapper;


    public Instructor toEntity(InstructorCreateRequest request) {
        return modelMapper.map(request, Instructor.class);
    }
    public InstructorResponse toDto(Instructor instructor) {
        InstructorResponse response = modelMapper.map(instructor, InstructorResponse.class);

        if (instructor.getCourses() != null && !instructor.getCourses().isEmpty()) {
            response.setCourses(
                    instructor.getCourses().stream()
                            .filter(course -> course.getIsDeleted() == null || !course.getIsDeleted())
                            .map(course -> modelMapper.map(course, InstructorCourseResponse.class))
                            .collect(Collectors.toList())
            );
        }

        return response;
    }

    public void updateEntity(Instructor instructor, InstructorUpdateDTORequest request) {
        modelMapper.map(request, instructor);
    }
}
