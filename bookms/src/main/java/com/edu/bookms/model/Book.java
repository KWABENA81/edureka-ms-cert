package com.edu.bookms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOK_TB")
public class Book  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

}