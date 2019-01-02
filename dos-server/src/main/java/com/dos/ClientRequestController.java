package com.dos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRequestController {

    private final RateLimitRequestsService RateLimitRequestsService;

    @Autowired
    public ClientRequestController(RateLimitRequestsService RateLimitRequestsService) {
        this.RateLimitRequestsService = RateLimitRequestsService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity rateLimited(@RequestParam("clientId") long id) {
        try {
            RateLimitRequestsService.addRequest(id);
        } catch (ThresholdExceededException e) {
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity(HttpStatus.OK);

    }

}
