package com.naukma.practice.myPet.exceptions;



import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.rmi.AccessException;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandlingControllerAdvice {

    @ExceptionHandler(AccessException.class)
    public ModelAndView accessError(HttpServletRequest req, Exception exception)
            throws Exception {
        // Rethrow annotated exceptions or they will be processed here instead.
        if (AnnotationUtils.findAnnotation(exception.getClass(),
                ResponseStatus.class) != null)
            throw exception;

        log.error("Request: " + req.getRequestURI() + " raised " + exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("status", 403);
        mav.setViewName("error_page");
        return mav;
    }
    @ExceptionHandler(AuthenticationException.class)
    public ModelAndView authenticationError(HttpServletRequest req, Exception exception)
            throws Exception {
        // Rethrow annotated exceptions or they will be processed here instead.
        if (AnnotationUtils.findAnnotation(exception.getClass(),
                ResponseStatus.class) != null)
            throw exception;

        log.error("Request: " + req.getRequestURI() + " raised " + exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("status", 401);
        mav.setViewName("error_page");
        return mav;
    }

    @ExceptionHandler(InvalidDataException.class)
    public ModelAndView invalidDataError(HttpServletRequest req, Exception exception)
            throws Exception {
        // Rethrow annotated exceptions or they will be processed here instead.
        if (AnnotationUtils.findAnnotation(exception.getClass(),
                ResponseStatus.class) != null)
            throw exception;

        log.error("Request: " + req.getRequestURI() + " raised " + exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("status", 400);
        mav.setViewName("error_page");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception)
            throws Exception {
        // Rethrow annotated exceptions or they will be processed here instead.
        if (AnnotationUtils.findAnnotation(exception.getClass(),
                ResponseStatus.class) != null)
            throw exception;

        log.error("Request: " + req.getRequestURI() + " raised " + exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("status", 500);
        mav.setViewName("error_page");
        return mav;
    }


}