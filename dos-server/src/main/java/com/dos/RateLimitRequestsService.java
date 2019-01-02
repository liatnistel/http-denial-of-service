package com.dos;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitRequestsService {
    private Map<Long, ClientInfo> servedClientsInfoMap;
    static final int maxRequests = 5;
    static final Long maxPeriod = 5L; //in seconds

    public RateLimitRequestsService() {
        this.servedClientsInfoMap = new ConcurrentHashMap<>();
        ;
    }

    public void addRequest(Long id) throws ThresholdExceededException {
        ClientInfo clientInfo = servedClientsInfoMap.get(id);
        if (clientInfo == null) {
            clientInfo = addClient(id);
        }
        if (!clientInfo.addRequest()) {
            throw new ThresholdExceededException();
        }
    }

    private ClientInfo addClient(Long clientId) {
        ClientInfo clientInfo = new ClientInfo(clientId);
        servedClientsInfoMap.put(clientId, clientInfo);
        return clientInfo;
    }
}
