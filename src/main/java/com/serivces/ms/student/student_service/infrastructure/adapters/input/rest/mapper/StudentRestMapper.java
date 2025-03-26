package com.serivces.ms.student.student_service.infrastructure.adapters.input.rest.mapper;

import com.serivces.ms.student.student_service.domain.model.Student;
import com.serivces.ms.student.student_service.infrastructure.adapters.input.rest.model.request.StudentCreateRequest;
import com.serivces.ms.student.student_service.infrastructure.adapters.input.rest.model.response.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import javax.swing.*;
import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface StudentRestMapper {

    Student toStudent(StudentCreateRequest request);

    StudentResponse toStudentResponse(Student student);

    List<StudentResponse> toStudentResponseList(List<Student> studentList);

}
