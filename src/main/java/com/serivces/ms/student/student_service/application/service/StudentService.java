package com.serivces.ms.student.student_service.application.service;

import com.serivces.ms.student.student_service.application.ports.input.StudentServicePort;
import com.serivces.ms.student.student_service.application.ports.output.StudentPersistencePort;
import com.serivces.ms.student.student_service.domain.exception.StudentNotFoundException;
import com.serivces.ms.student.student_service.domain.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//El servicio va implementar el puerto de entrada y va hacer uso del puerto de salida
@Service
@RequiredArgsConstructor
public class StudentService implements StudentServicePort {

    private  final StudentPersistencePort persistencePort;

    @Override
    public Student findById(Long id) {
        return persistencePort.findById(id)
                .orElseThrow(StudentNotFoundException::new);
    }//LLama al puerto de persintencia para buscar un estudiante por ID

    @Override
    public List<Student> findAll() {
        return persistencePort.findAll();
    }//Devuelve todos los estudiantes desde la base de datos (a través del puerto).

    @Override
    public Student save(Student student) {
        return persistencePort.save(student);
    }//Guarda un nuevo estudiante.

    @Override
    public Student update(Long id, Student student) {
        return persistencePort.findById(id)
                .map(savedStudent ->{
                    savedStudent.setFirstname(student.getFirstname());
                    savedStudent.setLastname(student.getLastname());
                    savedStudent.setAge(student.getAge());
                    savedStudent.setAddress(student.getAddress());
                    return persistencePort.save(savedStudent);
                        })
                .orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if (persistencePort.findById(id).isEmpty()) {
            throw new StudentNotFoundException();
        }
        persistencePort.deleteById(id);
    }
}
