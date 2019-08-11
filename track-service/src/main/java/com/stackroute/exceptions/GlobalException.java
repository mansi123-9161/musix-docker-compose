package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// Allows to write global code that can be applied to a wide range of controllers
@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
    //    For handling the errors for no track found
    @ExceptionHandler({TrackNotFoundException.class})
    public ResponseEntity<String> handleTrackNotFoundExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //    For handling the errors for track already exists
    @ExceptionHandler({TrackAlreadyExistException.class})
    public ResponseEntity<String> handleTrackAlreadyExistExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
