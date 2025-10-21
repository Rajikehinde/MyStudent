package com.My_Student.exception;

import com.My_Student.dto.ErrorResponseDto;
import com.My_Student.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandling {

    /**
     *
     * @param exception - runtime exception
     * @param webRequest
     * @return exception if error occurs with api
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception exception, WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     *
     * @param resourceNotFoundException - business exception
     * @param webRequest
     * @return exceptions when error occurs getting business data
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException, WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                resourceNotFoundException.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     *
     * @param studentAlreadyExistException - student exception
     * @param webRequest
     * @return exception when error occurs when getting student
     */
    @ExceptionHandler(StudentAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleStudentAlreadyExistException(StudentAlreadyExistException studentAlreadyExistException, WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                studentAlreadyExistException.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
