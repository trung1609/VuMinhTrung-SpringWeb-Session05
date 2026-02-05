package com.api.session05.service;

import com.api.session05.model.dto.request.PageRequestDTO;
import com.api.session05.model.dto.request.course_request.CourseCreateRequest;
import com.api.session05.model.dto.request.course_request.CourseUpdateDTORequest;
import com.api.session05.model.dto.response.PageResponseDTO;
import com.api.session05.model.dto.response.course_response.CourseResponse;
import com.api.session05.model.entity.CourseStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {

    List<CourseResponse> getAllCourse();

    PageResponseDTO<CourseResponse> getAllCourse(PageRequestDTO request);

    CourseResponse getCourseById(Long id);

    CourseResponse createCourse(CourseCreateRequest request);

    CourseResponse updateCourse(Long id, CourseUpdateDTORequest request);

    void deleteById(Long id);

    List<CourseResponse> findCourseByInstructorName(String instructorName);

    PageResponseDTO<CourseResponse> findAllByCourseStatus(CourseStatus courseStatus, PageRequestDTO request);
}
