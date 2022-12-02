package com.edu.bookms.common;

import com.edu.bookms.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Book book;
    private String issuerId;
    private String message;
}