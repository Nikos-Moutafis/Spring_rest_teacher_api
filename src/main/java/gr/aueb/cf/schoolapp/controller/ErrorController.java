package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.service.exceptions.StudentErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@ControllerAdvice
public class ErrorController {

    // Handle Requests to
    @GetMapping("/error")
    public String handleError() {
        return "error";
    }

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<Object> handleBadRequest(RuntimeException ex) {
//        StudentErrorResponse error = new StudentErrorResponse();
//
//        error.setStatus(HttpStatus.BAD_REQUEST.value());
//        error.setMessage(ex.getMessage());
//        error.setTimestamp(System.currentTimeMillis());
//
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

        String message = ex.getMessage();
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }


//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleExceptions(Exception ex){
//    StudentErrorResponse error = new StudentErrorResponse();
//
//    error.setStatus(HttpStatus.BAD_REQUEST.value());
//    error.setMessage("You inserted a string instead of int");
//    error.setTimestamp(System.currentTimeMillis());
//
//    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);    }

}
