package com.edu.issuerms.api;

import com.edu.issuerms.model.Issuer;
import com.edu.issuerms.service.IssuerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin(origins = {"${app.security.cors.origin}"})
@Api(value = "Issuer Class", protocols = "http")
@RequestMapping("/api")
public class IssuerResource {
    @Autowired
    private IssuerService issuerService;

    @ApiOperation(value = "Fetch all Issuers", response = Issuer.class, code = 200)
    @GetMapping(path = "/issuers")
    public ResponseEntity<List<Issuer>> issuers() {
        log.info("Start All Issuers retrieval");
        List<Issuer> issuers = issuerService.findAll().stream().collect(Collectors.toList());
        return ResponseEntity.ok(issuers);
    }

    @ApiOperation(value = "Fetch Issue by Id", response = Issuer.class, code = 200)
    @GetMapping(path = "/issuer/{id}")
    //@PrometheusTimeMethod(name = "issuer_resource_controller_issuer_by_id_duration_seconds", help = "Some helpful info here")
    public ResponseEntity<Issuer> issuerById(@PathVariable(value = "id") Long id) {
        Optional<Issuer> issuerOptional = issuerService.findById(id);
        if (issuerOptional.isPresent()) {
            log.info(" Issuer findById OK");
            return ResponseEntity.ok().body(issuerOptional.get());
        } else {
            log.error("FindById Issuer failed");
            return ResponseEntity.notFound().build();
        }
    }


    @ApiOperation(value = "Issue Book to IssuerCustomer", response = Issuer.class, code = 200)
    @PostMapping(path = "/add")
    public Issuer addIssuer(@RequestBody Issuer issuer) {
        return issuerService.issueBook(issuer);
    }

    @ApiOperation(value = "Delete / Cancel Book Issue", response = Issuer.class, code = 200)
    @DeleteMapping(path = "/cancel/{id}")
    //@RequestMapping(value ="/cancel/{id}" , method = RequestMethod.DELETE)
    public void cancelIssue(@PathVariable(value = "id") Long id) {
        log.info("Issuer with #id {} has cancelIssuer", id);
        boolean isRemoved = issuerService.delete(id);
        if (!isRemoved) {
            log.error("Issuer not found with id {}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else
            log.info("Issuer with #id {} has been deleted", id);
    }

}

