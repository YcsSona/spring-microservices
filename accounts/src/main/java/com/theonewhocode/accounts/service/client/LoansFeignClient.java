package com.theonewhocode.accounts.service.client;

import com.theonewhocode.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

// connects with the Eureka server at the runtime and get instance details with logical name cards
@FeignClient(name = "loans", fallback = LoansFallback.class)
public interface LoansFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<LoansDto> fetchLoan(@RequestHeader("eazybank-correlation-id") String correlationId, @RequestParam String mobileNumber);
}
