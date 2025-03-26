package com.serivces.ms.student.student_service.infrastructure.adapters.input.rest;

import com.serivces.ms.student.student_service.application.ports.input.StudentServicePort;
import com.serivces.ms.student.student_service.infrastructure.adapters.input.rest.mapper.StudentRestMapper;
import com.serivces.ms.student.student_service.infrastructure.adapters.input.rest.model.request.StudentCreateRequest;
import com.serivces.ms.student.student_service.infrastructure.adapters.input.rest.model.response.StudentResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentRestController {

    private final StudentRestMapper restMapper;
    private final StudentServicePort servicePort;

    @Operation(summary = "Obtener todos los estudiantes", description = "Devuelve una lista de todos los estudiantes registrados.")
    @GetMapping("/api")
    public List<StudentResponse> findAll(){
        return restMapper.toStudentResponseList(servicePort.findAll());
    }

    @Operation(summary = "Obtener todos los por id", description = "Devuelve un estudiante por id.")
    @GetMapping("/api/{id}")
    public StudentResponse findById(@PathVariable Long id){
        return restMapper.toStudentResponse(servicePort.findById(id));
    }

    @Operation(summary = "Crea un nuevo estudiante", description = "Crea un nuevo estudiantes con nuevos datos.")
    @PostMapping("/api")
    public ResponseEntity<StudentResponse> save(@Valid @RequestBody StudentCreateRequest request){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restMapper.toStudentResponse(
                        servicePort.save(restMapper.toStudent(request))
                ));
    }
    @Operation(summary = "Actualiza un estudiante", description = "Actualiza datos de un estudiante por Id.")
    @PutMapping("api/{id}")
    public StudentResponse update(@PathVariable Long id, @Valid @RequestBody StudentCreateRequest request ){
        return restMapper.toStudentResponse(
                servicePort.update(id,restMapper.toStudent(request)));
    }
    @Operation(summary = "Elimina un estudiante", description = "Elimina  un estudiante por Id.")
    @DeleteMapping("api/{id}")
    public  void delete(@PathVariable Long id){
        servicePort.deleteById(id);
    }

}
