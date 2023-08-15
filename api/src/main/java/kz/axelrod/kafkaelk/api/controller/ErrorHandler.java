package kz.axelrod.kafkaelk.api.controller;

import kz.axelrod.kafkaelk.api.dto.ApiResponse;
import kz.axelrod.kafkaelk.api.exception.BadRequestException;
import kz.axelrod.kafkaelk.api.exception.InternalServerErrorException;
import kz.axelrod.kafkaelk.api.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleError(Exception e) {
        log.error("Uncaught error", e);
        int status;
        String message = e.getMessage();
        if (e instanceof InternalServerErrorException) {
            status = 500;
        } else if (e instanceof BadRequestException) {
            status = 400;
        } else if (e instanceof UnauthorizedException) {
            status = 401;
        } else {
            status = 500;
        }
        return ResponseEntity.status(status).body(ApiResponse.fail(message));
    }
}
