package de.fh.kiel.advancedjava.pojomodel.controller;

import de.fh.kiel.advancedjava.pojomodel.exception.*;
import de.fh.kiel.advancedjava.pojomodel.model.ApiError;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler()
    public ResponseEntity<ApiError> defaultHandleConflict(Exception ex) {
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex));
    }

    @ExceptionHandler({AttributeAlreadyExistsException.class, AttributeDoesNotExistException.class, IsEmptyHullException.class, PackageDoesNotExistException.class, PackageNameNotAllowedException.class, PojoAlreadyExistsException.class, PojoDoesNotExistException.class})
    public ResponseEntity<ApiError> serverError(Exception ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> jsonParseException(Exception exception) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "The given JSON has an incorrect syntax: e.g. missing parentheses, invalid character", exception));
    }

    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public ResponseEntity<ApiError> invaldData(Exception exception) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "The given data has an incorrect schema: e.g. undefined field name", exception));
    }

    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
