package com.sritel.provisioning.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface AccountClient {
    Logger log = LoggerFactory.getLogger(AccountClient.class);

    @GetExchange("/api/v1/account/exists/{phoneNo}")
    @CircuitBreaker(name = "account", fallbackMethod = "fallbackMethod")
    @Retry(name = "account")
    boolean isAccountExists(@PathVariable String phoneNo);

    default boolean fallbackMethod(String phoneNo, Throwable throwable) {
        log.info("Cannot get account for phoneNo {}, failure reason: {}", phoneNo, throwable.getMessage());
        return false;
    }
}
