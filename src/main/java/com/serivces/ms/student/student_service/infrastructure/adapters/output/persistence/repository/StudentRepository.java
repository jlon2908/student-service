package com.serivces.ms.student.student_service.infrastructure.adapters.output.persistence.repository;

import com.serivces.ms.student.student_service.domain.model.Student;
import com.serivces.ms.student.student_service.infrastructure.adapters.output.persistence.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity,Long> {

}
