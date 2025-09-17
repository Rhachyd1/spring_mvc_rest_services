package com.rhd.learning.springMvcRestServices.exceptionHandler;

import org.springframework.http.ResponseEntity;

//@ControllerAdvice
public class ExceptionController  {
    //This is a way to setup a global exception
    //@ExceptionHandler(NotFoundException.class) 
    public ResponseEntity<Object> handleNotFound(){
        return ResponseEntity.notFound().build();
    }
}
