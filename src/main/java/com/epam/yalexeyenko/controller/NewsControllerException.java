package com.epam.yalexeyenko.controller;

public class NewsControllerException extends RuntimeException {
	public NewsControllerException() {
    }

    public NewsControllerException(String message) {
        super(message);
    }

    public NewsControllerException(String message, Exception exception) {
        super(message, exception);
    }
}
