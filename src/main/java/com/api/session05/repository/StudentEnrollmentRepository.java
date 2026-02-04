package com.api.session05.repository;

import com.api.session05.model.dto.response.student_enrollment_response.StudentEnrollmentResponse;
import com.api.session05.model.entity.Course;
import com.api.session05.model.entity.Student;
import com.api.session05.model.entity.StudentEnrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    void deleteByStudentIdAndCourseId(Long studentId, Long courseId);

    List<StudentEnrollment> searchStudentEnrollmentByStudentAndCourse(Student student, Course course);

    @Query("select new com.api.session05.model.dto.response.student_enrollment_response.StudentEnrollmentResponse(se.id, se.student.name, se.course.title, se.enrollAt) " +
            "from StudentEnrollment se " +
            "where se.course.id = :course_id and se.student.name ilike concat('%', :student_name, '%')")
    List<StudentEnrollmentResponse> findByStudentNameContainingIgnoreCaseAndCourseId(@Param("student_name") String studentName, @Param("course_id") Long courseId);

    @Query("select new com.api.session05.model.dto.response.student_enrollment_response.StudentEnrollmentResponse(ser.id, ser.student.name, ser.course.title, ser.enrollAt)" +
            "from StudentEnrollment ser where ser.course.title = :courseTitle")
    List<StudentEnrollmentResponse> findAllByCourseTitle(@Param("courseTitle") String courseTitle);
}
