package com.edu.issuerms.service;

import com.edu.issuerms.model.Issuer;
import com.edu.issuerms.repo.IssuerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class IssuerService implements IIssuerService {

     @Autowired
    private IssuerRepository issuerRepository;

    public Issuer issueBookToCustomer(Issuer issuer) {
        return null;
    }


    public Collection<Issuer> findAll() {
        return issuerRepository.findAll();
    }

    @Override
    public Issuer findById(Integer id) {
        return null;
    }

    @Override
    public Issuer findByIsbn(String isbn) {
        List<Issuer> issueList = issuerRepository.findByIsbn(isbn);
        return issueList.stream().findFirst().get();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

//    public List<Issuer> findByIsbn(String isbn) {
//        return issuerRepository.findByIsbn(isbn);
//    }

    public List<Issuer> findIssuerByCustomer(String custid) {
        return null;//issuerRepository.findByCustId(custid);
    }


//    public Issuer save(Issuer issuer) {
//        return issuerRepository.save(issuer);
//    }
//
//    public Issuer doBookIssuer(Issuer issuer) {
//        issuer.setIssuerConfirm(UUID.randomUUID().toString().replace("-", "").toUpperCase());
//        return issuerRepository.save(issuer);
//    }
}
