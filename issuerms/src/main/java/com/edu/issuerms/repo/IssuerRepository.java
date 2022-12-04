package com.edu.issuerms.repo;


import com.edu.issuerms.model.Issuer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
//@Transactional
public interface IssuerRepository extends JpaRepository<Issuer, Integer> {

    @Query("SELECT iss FROM Issuer iss WHERE iss.isbn=(:isbn)")
    List<Issuer> findByIsbn(@Param("isbn") String isbn);

    @Query("SELECT iss FROM Issuer iss WHERE iss.isbn=(:isbn)")
    List<Issuer> findIssuerByBookIsbn(@Param("isbn") String isbn);
}