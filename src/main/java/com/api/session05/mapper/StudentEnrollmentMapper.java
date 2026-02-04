package com.api.session05.mapper;

import com.api.session05.model.dto.response.course_response.CourseInstructorResponse;
import com.api.session05.model.dto.response.student_enrollment_response.EnrollmentDetailsDTOResponse;
import com.api.session05.model.dto.response.student_enrollment_response.StudentEnrollmentResponse;
import com.api.session05.model.dto.response.student_response.StudentResponse;
import com.api.session05.model.entity.StudentEnrollment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentEnrollmentMapper {

    @Autowired
    private ModelMapper modelMapper;

    public StudentEnrollmentResponse toDTO(StudentEnrollment studentEnrollment) {
        // Map Student
        StudentResponse studentResponse = StudentResponse.builder()
                .name(studentEnrollment.getStudent().getName())
                .email(studentEnrollment.getStudent().getEmail())
                .build();

        // Map Course with Instructor
        CourseInstructorResponse instructorResponse = null;
        if (studentEnrollment.getCourse().getInstructor() != null) {
            instructorResponse = CourseInstructorResponse.builder()
                    .name(studentEnrollment.getCourse().getInstructor().getName())
                    .build();
        }

        EnrollmentDetailsDTOResponse courseResponse = EnrollmentDetailsDTOResponse.builder()
                .id(studentEnrollment.getCourse().getId())
                .title(studentEnrollment.getCourse().getTitle())
                .status(studentEnrollment.getCourse().getStatus())
                .instructor(instructorResponse)
                .build();

        return StudentEnrollmentResponse.builder()
                .id(studentEnrollment.getId())
                .studentName(studentEnrollment.getStudent().getName())
                .courseTitle(studentEnrollment.getCourse().getTitle())
                .enrollmentDate(studentEnrollment.getEnrollAt())
                .build();
    }
}
