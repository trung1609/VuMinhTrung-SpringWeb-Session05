package com.api.session05.repository;


import com.api.session05.model.entity.Course;
import com.api.session05.model.entity.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c where c.instructor.name ilike concat('%', :instructorName, '%') ")
    List<Course> findAllByInstructorName(@Param("instructorName") String instructorName);

    @Query("select c from Course c where c.status = :courseStatus")
    List<Course> findAllByCourseStatus(@Param("courseStatus") CourseStatus courseStatus);

}
