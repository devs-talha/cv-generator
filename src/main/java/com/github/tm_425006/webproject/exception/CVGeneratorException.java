package com.github.tm_425006.webproject.exception;

public class CVGeneratorException extends Exception {
    public CVGeneratorException() {
    }

    public CVGeneratorException(Throwable cause) {
        super(cause);
    }

    public CVGeneratorException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return String.format("CVGeneratorException{ %s }", this.getMessage());
    }
}
