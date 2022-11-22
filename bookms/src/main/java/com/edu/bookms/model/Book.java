package com.edu.bookms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOKS")
public class Book implements Comparable<Book> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String isbn;
    private String title;
    private String author;
    private LocalDate publishedDate;
    private Integer issuedCopies;
    private Integer totalCopies;

    @Override
    public int compareTo(Book bk) {
        return this.isbn.compareTo(bk.isbn);
    }
}
