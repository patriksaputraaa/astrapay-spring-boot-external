package com.astrapay.controller.advice;

import com.astrapay.dto.res.BaseResponse;
import com.astrapay.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseResponse<Object>> handleCustomException(CustomException ex) {
        BaseResponse<Object> response = new BaseResponse<>();
        response.setMessage(ex.getMessage());
        response.setData(null);
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Object>> handleValidationException(MethodArgumentNotValidException ex) {
        BaseResponse<Object> response = new BaseResponse<>();
        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");
        response.setMessage(errorMessage);
        response.setData(null);
        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponse<Object>> handleMissingBody(HttpMessageNotReadableException ex) {
        BaseResponse<Object> response = new BaseResponse<>();
        response.setMessage("Request body is required");
        response.setData(null);
        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object>> handleGenericException(Exception ex) {
        BaseResponse<Object> response = new BaseResponse<>();
        response.setMessage("Internal server error: " + ex.getMessage());
        response.setData(null);
        return ResponseEntity.status(500).body(response);
    }
}
