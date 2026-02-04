package com.api.session05.model.dto.response.student_enrollment_response;

import com.api.session05.model.dto.response.course_response.CourseInstructorResponse;
import com.api.session05.model.entity.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentDetailsDTOResponse {
    private Long id;
    private String title;
    private CourseStatus status;
    private CourseInstructorResponse instructor;
}


