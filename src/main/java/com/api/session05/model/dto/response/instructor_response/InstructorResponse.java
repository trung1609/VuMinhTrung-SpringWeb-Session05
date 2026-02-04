package com.api.session05.model.dto.response.instructor_response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstructorResponse {
    private String name;
    private String email;
    private List<InstructorCourseResponse> courses = new ArrayList<>();
}
