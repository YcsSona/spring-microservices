package com.theonewhocode.accounts.service.client;

import com.theonewhocode.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

// connects with the Eureka server at the runtime and get instance details with logical name cards
@FeignClient(name = "cards", url = "http://cards:9000", fallback = CardsFallback.class)
public interface CardsFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCard(@RequestHeader("eazybank-correlation-id") String correlationId, @RequestParam String mobileNumber);
}
