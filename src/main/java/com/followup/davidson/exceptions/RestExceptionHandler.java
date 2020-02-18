package com.followup.davidson.exceptions;



import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity genericExceptionHandler(Exception ex) {
        log.debug("handling Exception...");
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity EntityNotFound(EntityNotFoundException ex) {
        log.debug("handling EntityNotFoundException...");
        return ResponseEntity.status(ex.getErrCode()).body(ex.getErrMsg());
    }
}
