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
@RequestMapping//(path="/books")
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

    //    public ResponseEntity<List<Issuer>> issuers() {
//
//        List<Issuer> issuers = issuerService.findAll().stream().collect(Collectors.toList());
//        return ResponseEntity.ok(issuers);
//    }
    //  Fetch Book
    @GetMapping("/fetchBook/{isdn}")
    public ResponseEntity<Book> fetchBook(@PathVariable String isbn) {
        Book book = bookService.findByIsbn(isbn);
        if (book != null) {
            log.info("Book found with book isbn {}", isbn);
            return ResponseEntity.ok(book);
        }
        log.error("book not found with book id {}", book);
        return ResponseEntity.notFound().build();
    }


    //  Fetch Book
    @GetMapping("/fetchBook/{id}")
    public ResponseEntity<Book> fetchBook(@PathVariable Integer id) {
        Book book = bookService.findById(id);
        if (book != null) {
            log.info("Book found with book id {}", id);
            return ResponseEntity.ok(book);
        }
        log.error("book not found with book id {}", book);
        return ResponseEntity.notFound().build();
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

    //  Add Book
    @PostMapping("/addBook")
    public TransactionResponse addBook(@RequestBody TransactionRequest transactionRequest) {
        return bookService.saveBook(transactionRequest);
    }

    //  Edit    , Update
    @PutMapping("/editBook/{id}")
    public Book editBook(@RequestBody Book book, @PathVariable Integer id) {
        Book book_db = bookService.findById(id);
        if (book_db != null) {
            book_db.setIsbn(book.getIsbn());
            book_db.setTitle(book.getTitle());
            book_db.setAuthor(book.getAuthor());
            book_db.setIssuedCopies(book.getIssuedCopies());
            book_db.setPublishedDate(book.getPublishedDate());

            return bookService.save(book_db);
        } else {
            book.setId(id);
            return bookService.save(book);
        }
    }


    @DeleteMapping("/deleteBook/{id}")
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
