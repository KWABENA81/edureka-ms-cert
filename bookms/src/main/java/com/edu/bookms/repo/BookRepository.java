package com.edu.bookms.repo;

import com.edu.bookms.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT bk FROM Book bk WHERE bk.isbn=(:isbn)")
    Optional<Book> findByIsbn(@Param("isbn") String isbn);

    @Query("SELECT bk FROM Book bk WHERE bk.issuerId=(:id)")
    List<Book> findByIssuerId(@Param("id") Long id);
}

