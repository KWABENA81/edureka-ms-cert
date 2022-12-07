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
    private Long issuerId;
    private String customerInfo;
    private String isbn;
    private Integer noOfCopies;
    private String issuerStatus;
    private String issuerTransactionId;
}
//( issuerId, customerInfo, isbn, noOfCopies, issuerStatus, issuerTransactionId)