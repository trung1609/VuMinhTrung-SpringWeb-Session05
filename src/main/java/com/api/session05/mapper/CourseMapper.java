package com.api.session05.mapper;

import com.api.session05.model.dto.request.course_request.CourseCreateRequest;
import com.api.session05.model.dto.request.course_request.CourseUpdateDTORequest;
import com.api.session05.model.dto.response.course_response.CourseInstructorResponse;
import com.api.session05.model.dto.response.course_response.CourseResponse;
import com.api.session05.model.dto.response.course_response.CourseResponseV2;
import com.api.session05.model.dto.response.student_response.StudentResponse;
import com.api.session05.model.entity.Course;
import com.api.session05.model.entity.Instructor;
import com.api.session05.repository.InstructorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CourseResponse toDto(Course course) {
        CourseResponse response = modelMapper.map(course, CourseResponse.class);

        if (course.getInstructor() != null) {
            response.setInstructor(modelMapper.map(course.getInstructor(), CourseInstructorResponse.class));
        }

        if (course.getEnrollments() != null && !course.getEnrollments().isEmpty()) {
            response.setStudent(course.getEnrollments().stream()
                    .map(enrollment -> modelMapper.map(enrollment.getStudent(), StudentResponse.class))
                    .toList());
        }
        return response;
    }

    public CourseResponseV2 toDtoV2(Course course) {
        return modelMapper.map(course, CourseResponseV2.class);
    }

    public Course toEntity(CourseCreateRequest request) {
        Course course = modelMapper.map(request, Course.class);

        if (request.getInstructorId() != null) {
            Instructor instructor = Instructor.builder()
                    .id(request.getInstructorId())
                    .build();
            course.setInstructor(instructor);
        }

        return course;
    }

    public void updateEntity(Course course, CourseUpdateDTORequest request) {
        modelMapper.map(request, course);

        if (request.getInstructorId() != null) {
            Instructor instructor = instructorRepository.findById(request.getInstructorId())
                    .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + request.getInstructorId()));
            course.setInstructor(instructor);
        }
    }
}
