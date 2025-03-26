package com.serivces.ms.student.student_service.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long id;

    private String firstname;

    private String lastname;

    private Integer age;

    private  String address;

}
