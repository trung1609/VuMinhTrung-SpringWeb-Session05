package com.api.session05.service.impl;


import com.api.session05.mapper.CourseMapper;
import com.api.session05.model.dto.request.PageRequestDTO;
import com.api.session05.model.dto.request.course_request.CourseCreateRequest;
import com.api.session05.model.dto.request.course_request.CourseUpdateDTORequest;
import com.api.session05.model.dto.response.course_response.CourseResponse;
import com.api.session05.model.entity.Course;
import com.api.session05.model.entity.CourseStatus;
import com.api.session05.repository.CourseRepository;
import com.api.session05.service.CourseService;
import com.api.session05.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<CourseResponse> getAllCourse() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .filter(course -> course.getIsDeleted() == false)
                .map(courseMapper::toDto).toList();
    }

    @Override
    public Page<CourseResponse> getAllCourse(PageRequestDTO request) {
        if (request.getSortBy() == null || request.getSortBy().isBlank()) {
            request.setSortBy("id");
        }

        if (request.getDirection() == null || request.getDirection().isBlank()) {
            request.setDirection("asc");
        }

        if (request.getPage() < 0) {
            request.setPage(0);
        }
        if (request.getSize() < 0) {
            request.setSize(5);
        }

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(Sort.Direction.ASC, "id"));
        return courseRepository.findAll(pageable).map(courseMapper::toDto);
    }

    @Override
    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        return courseMapper.toDto(course);
    }

    @Override
    public CourseResponse createCourse(CourseCreateRequest request) {
        if (instructorService.findInstructorById(request.getInstructorId()) == null) {
            throw new RuntimeException("Instructor not found with id: " + request.getInstructorId());
        }
        Course course = courseMapper.toEntity(request);
        return courseMapper.toDto(courseRepository.save(course));
    }

    @Override
    public CourseResponse updateCourse(Long id, CourseUpdateDTORequest request) {
        Course existingCourse = courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Course not found with id: " + id));
        courseMapper.updateEntity(existingCourse, request);
        return courseMapper.toDto(courseRepository.save(existingCourse));
    }

    @Override
    public void deleteById(Long id) {
        Course existingCourse = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        existingCourse.setIsDeleted(true);
        courseRepository.save(existingCourse);
    }

    @Override
    public List<CourseResponse> findCourseByInstructorName(String instructorName) {
        List<Course> courseList = courseRepository.findAllByInstructorName(instructorName);
        return courseList.stream().map(courseMapper::toDto).toList();
    }

    @Override
    public List<CourseResponse> findAllByCourseStatus(CourseStatus courseStatus) {
        return courseRepository.findAllByCourseStatus(courseStatus)
                .stream()
                .map(courseMapper::toDto)
                .toList();
    }
}

