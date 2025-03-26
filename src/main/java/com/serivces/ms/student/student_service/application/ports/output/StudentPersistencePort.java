package com.serivces.ms.student.student_service.application.ports.output;

import com.serivces.ms.student.student_service.domain.model.Student;

import java.util.List;
import java.util.Optional;
//Operaciones necesarias para persistir
public interface StudentPersistencePort {
    Optional<Student> findById(long id);

    List<Student> findAll();

    Student save(Student student);

    void deleteById(Long id);
}
