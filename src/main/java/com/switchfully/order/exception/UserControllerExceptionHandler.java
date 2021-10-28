package com.switchfully.order.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class UserControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(UserControllerExceptionHandler.class);

    @ExceptionHandler(AuthorizationException.class)
    protected void authorisationException(AuthorizationException exception, HttpServletResponse response) throws Exception {
        logger.error(exception.getMessage());
        response.sendError(HttpStatus.FORBIDDEN.value(), exception.getMessage());
    }
}
