package com.example.error;


import com.example.entity.Admin;
import com.example.exception.AdminNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomRequestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({UserNotFoundException.class})
    RestResponse<ResponseEntity<Object>> handleNotFoundSuchUser(UserNotFoundException exception, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(500, exception.getMessage());

        ResponseEntity<Object> responseEntity = new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

        return new RestResponse<ResponseEntity<Object>>(responseEntity, "USER PROBLEM", 1);
    }

    @ExceptionHandler({AdminNotFoundException.class})
    RestResponse<ResponseEntity<Object>> handleNotFoundSuchAdmin(AdminNotFoundException exception, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(500, exception.getMessage());

        ResponseEntity<Object> responseEntity = new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

        return new RestResponse<ResponseEntity<Object>>(responseEntity, "ADMIN PROBLEM", 9);
    }
}
