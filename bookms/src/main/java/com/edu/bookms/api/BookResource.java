package com.edu.bookms.api;


import com.edu.bookms.common.TransactionRequest;
import com.edu.bookms.common.TransactionResponse;
import com.edu.bookms.model.Book;
import com.edu.bookms.service.BookService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/book")
@AllArgsConstructor
@NoArgsConstructor
public class BookResource {

    @Autowired
    private BookService bookService;

    //  Fetch Books
    @GetMapping(path = "/books", produces = "application/json")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.findAll().stream().collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }


    //  Fetch Book by isbn
    @GetMapping("/books/{isdn}")
    public Book fetchBook(@PathVariable String isbn) {
        return bookService.findByIsbn(isbn).orElseThrow(() ->
                new com.edu.bookms.api.exception.BookNotFoundException(isbn)
        );
    }


    //  Fetch Book by id
    @GetMapping("/books/{id}")
    public Book fetchBook(@PathVariable Integer id) {
        return bookService.findById(id).orElseThrow(() ->
                new com.edu.bookms.api.exception.BookNotFoundException(id)
        );
    }


    //  Add Book
    @PostMapping("/add")
    public TransactionResponse issueBook(@RequestBody TransactionRequest transactionRequest) {
        return bookService.saveBook(transactionRequest);
    }

    //  Edit, Update
    @PutMapping("/edit/{id}")
    public Book editBook(@RequestBody Book nbook, @PathVariable Integer id) {
        return bookService.findById(id)
                .map(bk -> {
                    bk.setIsbn(nbook.getIsbn());
                    bk.setTitle(nbook.getTitle());
                    bk.setAuthor(nbook.getAuthor());
                    bk.setTotalCopies(nbook.getTotalCopies());
                    bk.setIssuedCopies(nbook.getIssuedCopies());
                    bk.setPublishedDate(nbook.getPublishedDate());

                    return bookService.save(bk);
                })
                .orElseGet(() -> {
                            nbook.setId(id);
                            bookService.save(nbook);
                            return bookService.save(nbook);
                        }
                );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteBook(@PathVariable Integer id) {
        boolean isRemoved = bookService.delete(id);
        if (!isRemoved) {
            log.info("Book not found with id {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Book with #id {} has been deleted", id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}

//    //  Add Book
//    @PostMapping("/createBook")
//    public ResponseEntity<Book> createBook(@RequestBody TransactionRequest transactionRequest)
//            throws URISyntaxException {
//        Book book = transactionRequest.getBook();
//        Issuer issuer = transactionRequest.getIssuer();
//        issuer.setIsbn(book.getIsbn());
//
//        Book bookSaved = bookService.save(book);
//        //  send rest call to issuer with isbn, & ...
//        return ResponseEntity.created(new URI(bookSaved.getId().toString())).body(bookSaved);
//    }