package com.api.session05.model.dto.response.student_enrollment_response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEnrollmentResponse {
    private Long id;
    private String studentName;
    private String courseTitle;
    private LocalDateTime enrollmentDate;
}
