package com.serivces.ms.student.student_service.application.ports.input;

import com.serivces.ms.student.student_service.StudentServiceApplication;
import com.serivces.ms.student.student_service.domain.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentServicePort {
    Student findById(Long id);
    List<Student> findAll();
    Student save(Student student);
    Student update(Long id,Student student);
    void deleteById(Long id);
}
