package br.com.desktop.login.advice;

import br.com.desktop.login.dto.ResponseErrorDTO;
import br.com.desktop.login.exception.BussinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<?> handleMyException(BussinessException execption) {
        ResponseErrorDTO response = new ResponseErrorDTO(execption.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
