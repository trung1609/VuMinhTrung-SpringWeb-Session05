package com.api.session05.model.dto.response.course_response;

import com.api.session05.model.dto.response.student_response.StudentResponse;
import com.api.session05.model.entity.CourseStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponse {
    private String title;
    private CourseInstructorResponse instructor;
    private List<StudentResponse> student;
    private CourseStatus status;

    public CourseResponse(String title, String instructorName, CourseStatus status) {
        this.title = title;
        this.instructor = CourseInstructorResponse.builder()
                .name(instructorName)
                .build();
        this.status = status;
    }
}
