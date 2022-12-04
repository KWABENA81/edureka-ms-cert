package com.edu.bookms.common;

import com.edu.bookms.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Book book;
    private String customerInfo;
    private String issuerStatus;
    private String issuerTransactionId;
    private String message;
}

//( issuerId, customerInfo, isbn, noOfCopies, issuerStatus, issuerTransactionId)