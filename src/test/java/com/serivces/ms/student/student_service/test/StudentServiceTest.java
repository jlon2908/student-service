package com.serivces.ms.student.student_service.test;

import com.serivces.ms.student.student_service.application.ports.output.StudentPersistencePort;
import com.serivces.ms.student.student_service.application.service.StudentService;
import com.serivces.ms.student.student_service.domain.exception.StudentNotFoundException;
import com.serivces.ms.student.student_service.domain.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {
    @Mock
    private StudentPersistencePort persistencePort;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void findById_shouldReturnStudent_whenStudentExists() {
        // Arrange
        Long id = 1L;
        Student mockStudent = new Student(id, "Ana", "Rojas", 22, "Calle 1");

        when(persistencePort.findById(id)).thenReturn(Optional.of(mockStudent));

        // Act
        Student result = studentService.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals("Ana", result.getFirstname());
        assertEquals("Rojas", result.getLastname());
        verify(persistencePort, times(1)).findById(id);
    }
    @Test
    void findById_shouldThrowException_whenStudentDoesNotExist() {
        // Arrange
        Long id = 99L;

        when(persistencePort.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(StudentNotFoundException.class, () -> studentService.findById(id));

        verify(persistencePort, times(1)).findById(id);
    }

    @Test
    void save_shouldReturnSavedStudent() {
        // Arrange
        Student studentToSave = new Student(null, "Carlos", "Lopez", 25, "Calle 5");
        Student savedStudent = new Student(1L, "Carlos", "Lopez", 25, "Calle 5");

        when(persistencePort.save(studentToSave)).thenReturn(savedStudent);

        // Act
        Student result = studentService.save(studentToSave);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Carlos", result.getFirstname());
        verify(persistencePort, times(1)).save(studentToSave);
    }

    @Test
    void update_shouldUpdateExistingStudent() {
        // Arrange
        Long id = 1L;

        Student existingStudent = new Student(id, "Ana", "Gómez", 20, "Calle A");
        Student updates = new Student(null, "Ana María", "Gómez", 21, "Calle B");
        Student updatedStudent = new Student(id, "Ana María", "Gómez", 21, "Calle B");

        when(persistencePort.findById(id)).thenReturn(Optional.of(existingStudent));
        when(persistencePort.save(any(Student.class))).thenReturn(updatedStudent);

        // Act
        Student result = studentService.update(id, updates);

        // Assert
        assertNotNull(result);
        assertEquals("Ana María", result.getFirstname());
        assertEquals(21, result.getAge());
        assertEquals("Calle B", result.getAddress());

        verify(persistencePort).findById(id);
        verify(persistencePort).save(existingStudent); // guarda el modificado
    }




}
