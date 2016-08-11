package com.epam.yalexeyenko.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {
	@ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public String defaultErrorHandler() {
        return "redirect:showMainPage";
    }
}
