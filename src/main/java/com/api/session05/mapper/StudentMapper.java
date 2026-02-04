package com.api.session05.mapper;

import com.api.session05.model.dto.request.student_request.StudentRequest;
import com.api.session05.model.dto.response.student_response.StudentResponse;
import com.api.session05.model.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    @Autowired
    private ModelMapper modelMapper;

    public StudentResponse toDTO(Student student){
        return modelMapper.map(student, StudentResponse.class);
    }

    public Student toEntity(StudentRequest request){
        return modelMapper.map(request, Student.class);
    }

    public void updateEntity(Student student, StudentRequest request){
        modelMapper.map(request, student);
    }
}
