package com.edu.issuerms.service;

import com.edu.issuerms.model.Issuer;

import java.util.Collection;
import java.util.Optional;

public interface IIssuerService {
    Collection<Issuer> findAll();

    Optional<Issuer> findById(Long id);

    Issuer findByIsbn(String isbn);

    boolean delete(Long id);

}
