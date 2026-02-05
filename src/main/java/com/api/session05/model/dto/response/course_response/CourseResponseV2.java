package com.api.session05.model.dto.response.course_response;

import com.api.session05.model.entity.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseV2 {
    private Long id;
    private String title;
    private CourseStatus status;
}
