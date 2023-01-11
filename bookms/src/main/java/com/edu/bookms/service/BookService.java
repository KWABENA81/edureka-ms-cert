package com.edu.bookms.service;


import com.edu.bookms.common.Issuer;
import com.edu.bookms.common.TransactionRequest;
import com.edu.bookms.common.TransactionResponse;
import com.edu.bookms.model.Book;
import com.edu.bookms.repo.BookRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RestTemplate template;

    @Override
    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByIssuerId(final Long id) {
        log.info(" Fetch Books by Issuer id {} ", id);
        return bookRepository.findByIssuerId(id);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public void delete(Long id) {
        // Optional<Book> optionalBook = bookRepository.findById(id);

        // if (optionalBook.isPresent()) {
        //     Book book = optionalBook.get();
        log.info(" BookService:  Book with id {} Deleted", id);
        bookRepository.deleteById(id);
        // }
    }

    public Book save(Book book) {
        bookRepository.save(book);
        return book;
    }

    public TransactionResponse saveBook(TransactionRequest request) {
        Book book = request.getBook();
        Issuer issuer = request.getIssuer();
        issuer.setIsbn(book.getIsbn());
        issuer.setCopies(book.getTotalCopies());

        Issuer issuerResponse = template
                .postForObject("http://ISSUERMS/issuer/doIssue", issuer, Issuer.class);
        String message = (issuerResponse.getStatus().equalsIgnoreCase("SUCCESS"))
                ? "Issuer SUCCESSFULLY" : "Invalid entry, FAILURE";

        bookRepository.save(book);
        return new TransactionResponse(book, issuerResponse.getCustInfo(),
                issuerResponse.getStatus(), issuerResponse.getTransactionId(), message);
    }

}
