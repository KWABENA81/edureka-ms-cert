package com.edu.bookms.api;

import com.edu.bookms.model.Book;
import com.edu.bookms.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/book")
public class BookResource {

    @Autowired
    private BookService bookService;

    //  Fetch Books
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.findAll().stream().collect(Collectors.toList());
    }

    //  Fetch Book
    @GetMapping("/books/{bookid}")
    public ResponseEntity<Book> findByBookId(@PathVariable String bookId) {
        Book book = bookService.findByBookId(bookId);
        if (book != null) {
            log.info("Book found with book id {}", bookId);
            return ResponseEntity.ok(book);
        }
        log.error("book not found with book id {}", book);
        return ResponseEntity.notFound().build();
    }

    //  Add Book
    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book)
            throws URISyntaxException {
        Book bookSaved = bookService.save(book);
        return ResponseEntity.created(new URI(bookSaved.getId().toString())).body(bookSaved);
    }

    //  Edit    , Update
    @PutMapping("/book/{id}")
    public Book updateStudent(@RequestBody Book book, @PathVariable Integer id) {
        Book book_db = bookService.findById(id);
        if (book_db != null) {

            book_db.setIsbn(book. getIsbn());
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

        @DeleteMapping("/books/{id}")
    public ResponseEntity<Integer> deleteBookById(@PathVariable Integer id) {
        boolean isRemoved = bookService.delete(id);
        if (!isRemoved) {
            log.info("Book not found with id {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Book with #id {} has been deleted", id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
