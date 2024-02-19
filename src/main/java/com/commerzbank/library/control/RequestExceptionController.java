package com.commerzbank.library.control;

import com.commerzbank.library.dto.request.ResponseErrorDto;
import com.commerzbank.library.exception.NotOwnerTryingToExtendReservationException;
import com.commerzbank.library.exception.RecordNotFoundException;
import com.commerzbank.library.exception.RequestValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
class RequestExceptionController {

@ExceptionHandler({RequestValidationException.class,RecordNotFoundException.class})
    ResponseEntity<ResponseErrorDto> handleValidationException(RuntimeException ex) {
    return ResponseEntity.badRequest().body(new ResponseErrorDto(ex.getMessage()));
}

    @ExceptionHandler({NotOwnerTryingToExtendReservationException.class})
    ResponseEntity<ResponseErrorDto> handleForbiddenException(RuntimeException ex) {
        return ResponseEntity.badRequest().body(new ResponseErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }


}
