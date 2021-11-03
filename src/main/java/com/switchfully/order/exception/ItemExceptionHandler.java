package com.switchfully.order.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ItemExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(UserExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    protected void illegalArgumentException(IllegalArgumentException exception, HttpServletResponse response) throws Exception {
        logger.error(exception.getMessage());
        response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage());
    }
}
