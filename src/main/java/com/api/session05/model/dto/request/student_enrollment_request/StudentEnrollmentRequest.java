package com.api.session05.model.dto.request.student_enrollment_request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEnrollmentRequest {
    private Long studentId;
    private Long courseId;
}
