package com.edu.issuerms.service;

import com.edu.issuerms.model.Issuer;
import com.edu.issuerms.repo.IssuerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class IssuerService implements IIssuerService {

//    @Autowired
//    private IssuerRepository issuerRepository;
//
//    public Collection<Issuer> findAll() {
//        return issuerRepository.findAll().stream().collect(Collectors.toList());
//    }
//
//    public List<Issuer> findByIsbn(String isbn) {
//        return issuerRepository.findByIsbn(isbn);
//    }
//
//    public List<Issuer> findByCustId(String custid) {
//        return issuerRepository.findByCustId(custid);
//    }
//
//    public Issuer save(Issuer issuer) {
//        return issuerRepository.save(issuer);
//    }
//
//    public Issuer doBookIssuer(Issuer issuer) {
//        issuer.setIssuerConfirm(UUID.randomUUID().toString().replace("-", "").toUpperCase());
//        return issuerRepository.save(issuer);
//    }
}