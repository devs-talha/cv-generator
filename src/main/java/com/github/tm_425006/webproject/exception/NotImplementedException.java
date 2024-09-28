package com.github.tm_425006.webproject.exception;

public class NotImplementedException extends CVGeneratorException {
    public NotImplementedException() {
    }

    public NotImplementedException(Throwable cause) {
        super(cause);
    }

    public NotImplementedException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "NotImplementedException{ " + this.getMessage() + " }";
    }
}
