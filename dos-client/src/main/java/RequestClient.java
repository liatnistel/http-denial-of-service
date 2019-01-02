
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.concurrent.ThreadLocalRandom;


public class RequestClient implements Runnable {

    private long clientId;
    private boolean threadInterrupted = false;
    private int MAX_RANDOM = 1000;
    private String baseUrl = "http://localhost:8080/";
    HttpRequestService httpRequestService;


    public RequestClient(long clientId) {
        this.clientId = clientId;
        httpRequestService = new HttpRequestService();
    }

    @Override
    public void run() {
        while (!threadInterrupted()) {
            CloseableHttpResponse response = httpRequestService.sendRequest(baseUrl, clientId);
            if (response != null) {
                System.out.println(clientId + " : " + response.getStatusLine().getStatusCode());
            }
            try {
                Thread.sleep(getRandomSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private boolean threadInterrupted() {
        return threadInterrupted;
    }

    public void shutDownClient() {
        threadInterrupted = true;
    }

    private long getRandomSleepTime() {
        return ThreadLocalRandom.current().nextInt(MAX_RANDOM);
    }
}
