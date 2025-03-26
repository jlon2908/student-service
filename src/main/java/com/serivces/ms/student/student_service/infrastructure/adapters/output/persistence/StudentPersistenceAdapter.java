package com.serivces.ms.student.student_service.infrastructure.adapters.output.persistence;

import com.serivces.ms.student.student_service.application.ports.input.StudentServicePort;
import com.serivces.ms.student.student_service.application.ports.output.StudentPersistencePort;
import com.serivces.ms.student.student_service.domain.model.Student;
import com.serivces.ms.student.student_service.infrastructure.adapters.output.persistence.mapper.StudentPersistenceMapper;
import com.serivces.ms.student.student_service.infrastructure.adapters.output.persistence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class StudentPersistenceAdapter implements StudentPersistencePort {

    private final StudentRepository repository;
    private  final StudentPersistenceMapper mapper;

    @Override
    public Optional<Student> findById(long id) {
        return repository.findById(id)
                .map(mapper::toStudent);
    }

    @Override
    public List<Student> findAll() {
        return mapper.toStudentList(repository.findAll());
    }

    @Override
    public Student save(Student student) {
        return mapper.toStudent(repository.save(mapper.toStudentEntity(student)));
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }
}
