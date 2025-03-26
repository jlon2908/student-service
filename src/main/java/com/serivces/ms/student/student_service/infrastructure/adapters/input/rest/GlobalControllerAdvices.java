package com.serivces.ms.student.student_service.infrastructure.adapters.input.rest;

import com.serivces.ms.student.student_service.domain.exception.StudentNotFoundException;
import com.serivces.ms.student.student_service.domain.model.ErrorResponse;
import com.serivces.ms.student.student_service.utils.ErrorCatalog;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerAdvices {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudentNotFoundException.class)
    public ErrorResponse handleStudentNotFoundException() {
        return ErrorResponse.builder()
                .code(ErrorCatalog.STUDENT_NOT_FOUND.getCode())
                .message(ErrorCatalog.STUDENT_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse MethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        BindingResult result=exception.getBindingResult();
        return ErrorResponse.builder()
                .code(ErrorCatalog.INVALID_STUDENT.getCode())
                .message(ErrorCatalog.INVALID_STUDENT.getMessage())
                .details(result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()))

                .timestamp(LocalDateTime.now())
                .build();
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericError(Exception exception, HttpServletRequest request) {
        String path = request.getRequestURI();

        // ⛔ IGNORAR rutas de Swagger para que no las intercepte
        if (path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs")) {
            throw new RuntimeException(exception); // permite que Swagger maneje el error
        }

        return ErrorResponse.builder()
                .code(ErrorCatalog.GENERIC_ERROR.getCode())
                .message(ErrorCatalog.GENERIC_ERROR.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }



}
