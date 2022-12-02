package com.edu.issuerms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ISSUER_TB")
public class Issuer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cust_id")
    private int custId;

    @Column(name = "isbn", nullable = false, length = 30)
    private String isbn;

    @Column(name = "issuer_confirm", nullable = true, length = 50)
    private String issuerConfirm;

    @Column(name = "issuer_results", nullable = false, length = 15)
    private String issuerResults;

    @Column(name = "no_of_copies")
    private Integer noOfCopies;
}