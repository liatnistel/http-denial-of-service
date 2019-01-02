package com.dos;

import org.isomorphism.util.TokenBucket;
import org.isomorphism.util.TokenBuckets;

import java.util.concurrent.TimeUnit;

import static com.dos.RateLimitRequestsService.maxPeriod;

public class ClientInfo {
    private Long clientId;
    private TokenBucket bucket;

    public ClientInfo(Long clientId) {
        this.clientId = clientId;
        this.bucket = TokenBuckets.builder()
                .withCapacity(RateLimitRequestsService.maxRequests)
                .withFixedIntervalRefillStrategy(RateLimitRequestsService.maxRequests, maxPeriod, TimeUnit.SECONDS)
                .build();
    }

    public boolean addRequest() {
        return bucket.tryConsume();
    }

    public Long getClientId() {
        return clientId;
    }
}
