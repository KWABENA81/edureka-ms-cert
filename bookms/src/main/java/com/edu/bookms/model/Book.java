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
@Table(name = "BOOK_TB")
public class Book implements Comparable<Book> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "isbn", nullable = false, length = 45)
    private String isbn;

    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Column(name = "author", nullable = false, length = 145)
    private String author;

    @Column(name = "published_date", nullable = false)
    private LocalDate publishedDate = LocalDate.now();

    @Column(name = "issued_copies")
    private Integer issuedCopies;

    @Column(name = "total_copies")
    private Integer totalCopies;

    @Override
    public int compareTo(Book bk) {
        return this.isbn.compareTo(bk.isbn);
    }
}