package com.edu.issuerms.service;

import com.edu.issuerms.model.Issuer;

import java.util.Collection;

public interface IIssuerService {
    Collection<Issuer> findAll();

    Issuer findById(Integer id);

    Issuer findByIsbn(String isbn);

    boolean delete(Integer id);

}
