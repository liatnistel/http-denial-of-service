package com.dos

import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class ClientRequestControllerTest extends Specification {

    def 'test controller'(){
        setup:
        RateLimitRequestsService requestsService = Mock(RateLimitRequestsService)
        ClientRequestController controller = new ClientRequestController(requestsService);
        def mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
        def request = get("/").param('clientId','0');


        when:
        def response = mockMvc.perform(request).andReturn().response

        then:
        response.status == 200



    }

}



