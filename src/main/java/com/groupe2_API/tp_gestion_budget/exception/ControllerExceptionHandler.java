package com.groupe2_API.tp_gestion_budget.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundException(NotFoundException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return errorMessage;
    }

    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ErrorMessage notFoundException(DuplicateException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.OK.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return errorMessage;
    }

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ErrorMessage notFoundException(NoContentException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NO_CONTENT.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return errorMessage;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalException(NotFoundException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return errorMessage;
    }

}
