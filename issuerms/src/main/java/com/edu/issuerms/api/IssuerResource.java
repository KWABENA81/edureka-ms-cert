package com.edu.issuerms.api;

import com.edu.issuerms.model.Issuer;
import com.edu.issuerms.service.IssuerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/issuer")
public class IssuerResource {
    @Autowired
    private IssuerService issuerService;

    //  Fetch all issers
    @GetMapping(path = "/issuers", produces = "application/json")//("/issuers")
    public ResponseEntity<List<Issuer>> issuers() {
        List<Issuer> issuers = issuerService.findAll().stream().collect(Collectors.toList());
        return ResponseEntity.ok(issuers);
    }

    //  Fetch Issuer by isbn / bookid
    @GetMapping("/issuers/{isbn}")
    public Issuer fetchIssuer(@PathVariable String isbn) {
        return issuerService.findByIsbn(isbn);
    }

    //  Fetch Issuer by custId
    @GetMapping("/issuers/{custid}")
    public List<Issuer> findIssuerByCustomer(@PathVariable String custid) {
        return issuerService.findIssuerByCustomer(custid);
    }

    //  Issue Book to IssuerCustomer
    @PostMapping("/issueBook")
    public Issuer issueBook(@RequestBody Issuer issuer) {
        return issuerService.issueBook(issuer);
    }

    @GetMapping("/issuerBook")
    public Issuer findIssuerByBookIsbn(@PathVariable String bookIsbn){
        return issuerService.findIssuerByBookIsbn(bookIsbn);
    }

//    @PutMapping("")
//    @PutMapping("/book/{id}")
//    public Book updateBook(@RequestBody Book book, @PathVariable Integer id) {
}