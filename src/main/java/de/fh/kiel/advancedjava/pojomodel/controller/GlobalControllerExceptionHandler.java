package de.fh.kiel.advancedjava.pojomodel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> defaultHandleConflict(Exception ex) {
        return ResponseEntity.status(HttpStatus.MULTI_STATUS).build();
    }
}
