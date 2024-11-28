package fr.diginamic.hello.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HelloControllerAdvice {

    @ExceptionHandler({ FunctionalException.class})
    protected ResponseEntity<String> traiterErreurs(FunctionalException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
