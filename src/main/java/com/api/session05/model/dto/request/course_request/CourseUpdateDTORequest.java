package com.api.session05.model.dto.request.course_request;

import com.api.session05.model.entity.CourseStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseUpdateDTORequest {
    private String title;
    private CourseStatus status;
    private Long instructorId;
}
