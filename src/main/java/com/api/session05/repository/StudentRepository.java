package com.api.session05.repository;

import com.api.session05.model.dto.response.student_response.StudentResponse;
import com.api.session05.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select new com.api.session05.model.dto.response.student_response.StudentResponse(s.name, s.email) from Student s " +
           "join StudentEnrollment se on s.id = se.student.id " +
           "join Course c on se.course.id = c.id " +
           "where c.title = :courseTitle")
    List<StudentResponse> findAllStudentByCourseTitle(@Param("courseTitle") String courseTitle);
}
