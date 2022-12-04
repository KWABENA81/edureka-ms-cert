package com.edu.issuerms.service;

import com.edu.issuerms.model.Issuer;
import com.edu.issuerms.repo.IssuerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class IssuerService implements IIssuerService {

    @Autowired
    private IssuerRepository issuerRepository;


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

    public String customerBookIssuing() {
        return new Random().nextBoolean() ? "SUCCESS" : "FAILURE";
    }

    public Issuer issueBook(Issuer issuer) {
        issuer.setIssuerStatus(customerBookIssuing());
        issuer.setIssuerTransactionId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        issuer.setCustomerInfo("4164587787");
        issuer.setIsbn("DEFAULT");
        issuer.setNoOfCopies(102);
        return issuerRepository.save(issuer);
    }

    public Issuer findIssuerByBookIsbn(String bookIsbn) {
        List<Issuer> list = issuerRepository.findIssuerByBookIsbn(bookIsbn);
        return (!list.isEmpty()) ? list.stream().findFirst().get() :
                new Issuer(999, "nul", "nul", 0, "nul", "nul");
    }

//    public Issuer save(Issuer issuer) {
//        return issuerRepository.save(issuer);
//    }
//
//    public Issuer doBookIssuer(Issuer issuer) {
//        issuer.setIssuerStatus(UUID.randomUUID().toString().replace("-", "").toUpperCase());
//        return issuerRepository.save(issuer);
//    }
}
