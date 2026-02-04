package com.api.session05.service;


import com.api.session05.model.dto.request.instructor_request.InstructorCreateRequest;
import com.api.session05.model.dto.request.instructor_request.InstructorUpdateDTORequest;
import com.api.session05.model.dto.response.instructor_response.InstructorResponse;

import java.util.List;

public interface InstructorService {

    InstructorResponse findInstructorById(Long id);

    List<InstructorResponse> findAllInstructors();

    InstructorResponse createInstructor(InstructorCreateRequest request);

    InstructorResponse updateInstructor(Long id, InstructorUpdateDTORequest request);

    void deleteById(Long id);
}
