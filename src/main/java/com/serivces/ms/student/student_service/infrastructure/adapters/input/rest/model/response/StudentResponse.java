package com.serivces.ms.student.student_service.infrastructure.adapters.input.rest.model.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private Long id;

    private String firstname;

    private String lastname;

    private Integer age;

    private  String address;

}
