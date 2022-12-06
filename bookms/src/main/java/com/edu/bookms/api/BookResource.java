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
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping(path = "/api")
public class BookResource {

    @Autowired
    private BookService bookService;

    //  Fetch Books
    @GetMapping(path = "/books")
    public ResponseEntity<List<Book>> books() {
        log.info("Start All Books retrieval");
        List<Book> books = bookService.findAll().stream().collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }


    //  Fetch Book by id
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> booksById(@PathVariable(value = "id") Integer id) {
        Optional<Book> bookOptional = bookService.findById(id);
        if (bookOptional.isPresent()) {
            log.info(" Books findById OK");
            return ResponseEntity.ok().body(bookOptional.get());
        } else {
            log.info("Start findById Books failed");
            return ResponseEntity.notFound().build();
        }
    }


    //  Add Book
    @PostMapping("/add")
    public TransactionResponse issueBook(@RequestBody TransactionRequest transactionRequest) {
        return bookService.saveBook(transactionRequest);
    }

    //  Edit, Update
    @PutMapping("/edit/{id}")
    public Book editBook(@RequestBody Book nbook, @PathVariable(value = "id") Integer id) {
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
    public ResponseEntity<Integer> deleteBook(@PathVariable(value = "id") Integer id) {
        log.info("Book not found with id {}", id);
        boolean isRemoved = bookService.delete(id);
        log.info("Book not found with id {}", isRemoved);
        if (!isRemoved) {
            log.info("Book not found with id {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Book with #id {} has been deleted", id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}

