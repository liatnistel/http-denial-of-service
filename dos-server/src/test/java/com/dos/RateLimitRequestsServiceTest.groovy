package com.dos

import spock.lang.Specification

class RateLimitRequestsServiceTest extends Specification {

    RateLimitRequestsService rateLimitRequestsService = new RateLimitRequestsService();

    def "Register more requests then max requests in time frame"(){
        setup:
        Long clientId = 3

        when:
        (0..6).each {
            rateLimitRequestsService.addRequest(clientId)
        }

        then:
        thrown(ThresholdExceededException)
    }

    def "Register more requests then max requests in longer than time frame"(){
        setup:
        Long clientId = 6

        when:
        (0..6).each {
            rateLimitRequestsService.addRequest(clientId)
            sleep(1500)
        }

        then:
        notThrown(ThresholdExceededException)
    }
}
