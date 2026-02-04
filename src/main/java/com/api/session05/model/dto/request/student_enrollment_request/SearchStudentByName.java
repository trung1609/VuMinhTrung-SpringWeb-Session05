package com.api.session05.model.dto.request.student_enrollment_request;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchStudentByName {
    private String studentName;
    private Long courseId;
}
