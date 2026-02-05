package com.api.session05.repository;


import com.api.session05.model.dto.response.course_response.CourseResponse;
import com.api.session05.model.dto.response.course_response.CourseResponseV2;
import com.api.session05.model.entity.Course;
import com.api.session05.model.entity.CourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c where c.instructor.name ilike concat('%', :instructorName, '%') ")
    List<Course> findAllByInstructorName(@Param("instructorName") String instructorName);

    @Query("select new com.api.session05.model.dto.response.course_response.CourseResponse(c.title, c.instructor.name, c.status) from Course c where c.status = :status")
    Page<CourseResponse> findAllByStatus(@Param("status") CourseStatus status, Pageable pageable);

    @Query("select new com.api.session05.model.dto.response.course_response.CourseResponseV2(c.id, c.title ,c.status) from Course c where :status is null or c.status = :status")
    Page<CourseResponseV2> findAllByStatusV2(@Param("status") CourseStatus status, Pageable pageable);

    @Query("select new com.api.session05.model.dto.response.course_response.CourseResponseV2(c.id, c.title, c.status) from Course c where (:title is null or lower(c.title) like concat('%', lower(cast(:title as string)) , '%')) ")
    Page<CourseResponseV2> findAllByCourseTitle(@Param("title") String title, Pageable pageable);
}
