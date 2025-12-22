package com.sai.inventory.exception;

import com.sai.inventory.util.ApplicationConstant;
import com.sai.inventory.util.ErrorResponseConstant;
import com.sai.inventory.util.ResponseBuilder;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UsersErrorHandling {

    Logger logger = LoggerFactory.getLogger(UsersErrorHandling.class);

    @ExceptionHandler()
    public ResponseEntity<Object> UserNotFoundHandler(UserNotFoundException ex) {
        logger.error("UserNotFoundException Error : " + ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    public ResponseEntity<Object> validationException(ValidationException ex) {
        logger.error("ValidationException Error : " + ex.getMessage());
        return ResponseBuilder.getValidationErrorResponse(ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler()
    public ResponseEntity<Object> invalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException ex) {
        logger.error("InvalidDataAccessResourceUsageException Error : " + ex.getMessage());
        return ResponseBuilder.getErrorResponse(ex.getMessage());
    }

    @ExceptionHandler()
    public ResponseEntity<Object> exception(Exception ex) {
        logger.error("Exception Error : " + ex.getMessage());
        return ResponseBuilder.getErrorResponse(ex.getMessage());
    }

    @ExceptionHandler()
    public ResponseEntity<Object> validationException(ExpiredJwtException ex) {
        logger.error("ExpiredJwtException Error : " + ex.getMessage());
        ErrorDetails details = new ErrorDetails();
        details.setMessage(ApplicationConstant.FAILURE_MSG);
        details.setErrorCode(ApplicationConstant.API_FAILURE);
        details.setErrorTypeCode(ErrorResponseConstant.JWT_EXPIRED);
        details.setErrorType(ErrorResponseConstant.JWT_EXPIRED_MSG);
        return ResponseBuilder.getErrorResponse(details, HttpStatus.UNAUTHORIZED);

    }
}
