package com.api.session05.model.dto.request.instructor_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstructorCreateRequest {
    private String name;
    private String email;
}
