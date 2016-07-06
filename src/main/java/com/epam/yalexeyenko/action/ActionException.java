package com.epam.yalexeyenko.action;

public class ActionException extends RuntimeException {
	public ActionException() {
    }

    public ActionException(String message) {
        super(message);
    }

    public ActionException(String message, Exception exception) {
        super(message, exception);
    }
}
