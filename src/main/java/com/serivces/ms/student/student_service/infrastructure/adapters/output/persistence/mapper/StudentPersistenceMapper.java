package com.serivces.ms.student.student_service.infrastructure.adapters.output.persistence.mapper;

import com.serivces.ms.student.student_service.domain.model.Student;
import com.serivces.ms.student.student_service.infrastructure.adapters.output.persistence.entity.StudentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentPersistenceMapper {
    StudentEntity toStudentEntity(Student student);

    Student toStudent(StudentEntity entity);

    List<Student> toStudentList(List<StudentEntity> entityList);

    }

