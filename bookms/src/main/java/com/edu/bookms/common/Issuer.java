package com.edu.bookms.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Issuer {

    private Integer id;

    private int custId;

    private String isbn;
    private String issuerId;

    private String issuerConfirm;

    private Integer noOfCopies;
}