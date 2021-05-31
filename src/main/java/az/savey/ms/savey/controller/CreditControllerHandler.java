package az.savey.ms.savey.controller;

import az.savey.ms.savey.exception.CreditException;
import az.savey.ms.savey.exception.StatusCode;
import az.savey.ms.savey.model.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CreditControllerHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CreditException.class)
    public ResponseEntity<BaseResponse> applicationException(CreditException e) {
        return new ResponseEntity<>(setMessage(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    private BaseResponse setMessage(String message) {
        BaseResponse baseResponse = new BaseResponse(StatusCode.ERROR);
        baseResponse.setError(message);
        return baseResponse;
    }
}
