package com.edu.bookms.api.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Integer id) {
        super("Could not find book " + id);
    }

    public BookNotFoundException(String isbn) {
        super("Could not find book " + isbn);
    }
}
