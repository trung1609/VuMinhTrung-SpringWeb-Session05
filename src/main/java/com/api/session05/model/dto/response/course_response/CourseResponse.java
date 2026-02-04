package com.api.session05.model.dto.response.course_response;

import com.api.session05.model.dto.response.student_response.StudentResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {
    private String title;
    private CourseInstructorResponse instructor;
    private List<StudentResponse> student;
}
