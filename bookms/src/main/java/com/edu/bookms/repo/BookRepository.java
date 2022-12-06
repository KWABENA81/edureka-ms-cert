package com.edu.bookms.repo;

import com.edu.bookms.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT bk FROM Book bk WHERE bk.isbn=(:isbn)")
   // List<Book>
    Optional<Book> findByIsbn(@Param("isbn") String isbn);
}

