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

    // Fetch Books
    @GetMapping(path = "/books")
    public ResponseEntity<List<Book>> books() {
        log.info("Start All Books retrieval");
        List<Book> books = bookService.findAll().stream().collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

    // Fetch Book by id
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> booksById(@PathVariable(value = "id") Long id) {
        Optional<Book> bookOptional = bookService.findById(id);
        if (bookOptional.isPresent()) {
            log.info(" Books findById OK");
            return ResponseEntity.ok().body(bookOptional.get());
        } else {
            log.info("Start findById Books failed");
            return ResponseEntity.notFound().build();
        }
    }

    // Add Book
    @PostMapping("/add")
    public TransactionResponse issueBook(@RequestBody TransactionRequest transactionRequest) {
        return bookService.saveBook(transactionRequest);
    }

    // Edit, Update
    @PutMapping("/edit/{id}")
    public Book editBook(@RequestBody Book nbook, @PathVariable(value = "id") Long id) {
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
                });
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteBook(@PathVariable(value = "id") Long id) {
        try {
            log.info("START:  Book with id {} Deleted", id);
            bookService.delete(id);
            log.info("FINISHED:  Book with id {} Deleted", id);
        } catch (Exception ex) {
            log.info("FAILED:  Book with id {} Deleted", id);
        }

    }

//     @RequestMapping("/error")
// public String handleError(HttpServletRequest request) {
//     Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    
//     if (status != null) {
//         Integer statusCode = Integer.valueOf(status.toString());
    
//         if(statusCode == HttpStatus.NOT_FOUND.value()) {
//             return "error-404";
//         }
//         else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//             return "error-500";
//         }
//     }
//     return "error";
// }
}
