package com.edu.issuerms.api.exception;

public class IssuerNotFoundException extends RuntimeException {
    public IssuerNotFoundException(Long id) {
        super("Could not find book " + id);
    }

    public IssuerNotFoundException(String isbn) {
        super("Could not find book " + isbn);
    }
}
