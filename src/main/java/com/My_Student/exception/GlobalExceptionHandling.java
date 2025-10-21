package com.My_Student.exception;

import com.My_Student.dto.ErrorResponseDto;
import com.My_Student.dto.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

    /**
     *
     * @param ex - validation exception
     * @param webRequest
     * @return exception if error occurs with validation fields
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();
        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });

        return super.handleMethodArgumentNotValid(ex, headers, status, webRequest);
    }

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
                HttpStatus.NOT_FOUND,
                resourceNotFoundException.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity(errorResponseDto, HttpStatus.NOT_FOUND);
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
                HttpStatus.BAD_REQUEST,
                studentAlreadyExistException.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
