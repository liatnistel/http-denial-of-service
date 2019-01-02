import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;
import java.net.URISyntaxException;

public class HttpRequestService {
    private CloseableHttpClient client;

    public HttpRequestService() {
        this.client = HttpClients.createDefault();
    }

    public HttpRequestService(CloseableHttpClient clients) {
        this.client = clients;
    }

    public CloseableHttpResponse sendRequest(String baseUrl, long clientId) {
        URIBuilder builder = new URIBuilder();
        builder.setPath(baseUrl).setParameter("clientId", String.valueOf(clientId));

        URI uri;
        try {
            uri = builder.build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.out.println("Faild to build URI");
            return null;
        }
        HttpGet httpGet = new HttpGet(uri);
        try {
            return client.execute(httpGet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
