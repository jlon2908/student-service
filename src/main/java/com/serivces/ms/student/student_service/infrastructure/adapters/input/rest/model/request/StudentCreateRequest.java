package com.serivces.ms.student.student_service.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateRequest {


    @NotBlank(message = "El campo de primer nombre no puede ser nulo ")
    private String firstname;

    @NotBlank(message = "El campo de segundo nombre no puede ser nulo ")
    private String lastname;

    @NotNull(message = "El campo de edad no puede ser nulo ")
    private  String age;

    @NotBlank(message = "El campo de direccion no puede ser nulo ")
    private String address;
}
