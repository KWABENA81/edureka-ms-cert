package com.edu.issuerms.common;

import com.edu.issuerms.model.Issuer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class IssuerRequest {
    private Customer customer;
    private Issuer issuer;
}