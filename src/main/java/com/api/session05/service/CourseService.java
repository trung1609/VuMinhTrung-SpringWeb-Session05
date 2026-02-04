package com.api.session05.service;

import com.api.session05.model.dto.request.course_request.CourseCreateRequest;
import com.api.session05.model.dto.request.course_request.CourseUpdateDTORequest;
import com.api.session05.model.dto.response.course_response.CourseResponse;
import com.api.session05.model.entity.CourseStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {

    List<CourseResponse> getAllCourse();

    Page<CourseResponse> getAllCourse(int page, int size, String sortBy, String direction);

    CourseResponse getCourseById(Long id);

    CourseResponse createCourse(CourseCreateRequest request);

    CourseResponse updateCourse(Long id, CourseUpdateDTORequest request);

    void deleteById(Long id);

    List<CourseResponse> findCourseByInstructorName(String instructorName);

    List<CourseResponse> findAllByCourseStatus(CourseStatus courseStatus);
}
