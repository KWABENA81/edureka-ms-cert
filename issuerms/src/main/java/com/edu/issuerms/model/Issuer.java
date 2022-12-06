package com.edu.issuerms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ISSUER_TB")
public class Issuer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "customer_info", length = 30)
    private String customerInfo;

    @Column(name = "isbn", nullable = false, length = 30)
    private String isbn;
    @Column(name = "no_of_copies")
    private Integer noOfCopies;

    @Column(name = "issuer_status", length = 150)
    private String issuerStatus;

    @Column(name = "issuer_transaction_id", length = 150)
    private String issuerTransactionId;

}
//( issuer_id, customer_info, isbn, no_of_copies, issuer_status, issuer_transaction_id)
//( issuerId, customerInfo, isbn, noOfCopies, issuerStatus, issuerTransactionId)