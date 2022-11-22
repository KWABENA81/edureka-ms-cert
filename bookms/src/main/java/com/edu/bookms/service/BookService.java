package com.edu.bookms.service;

import com.edu.bookms.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookService booksRepository;

    @Override
    public Collection<Book> findAll() {
        return booksRepository.findAll();
    }

    @Override
    public Book findById(Integer id) {
        return booksRepository.findById(id);
    }

    public Book save(Book book) {
        return booksRepository.save(book);
    }

    public Book findByIsbn(String isbn) {
        return null;
    }

    public boolean delete(Integer id) {
        return false;
    }
}
