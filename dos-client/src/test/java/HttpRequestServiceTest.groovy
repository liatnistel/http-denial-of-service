import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import spock.lang.Specification

class HttpRequestServiceTest extends Specification {

    HttpRequestService service;

    def 'Send successfull request'() {
        setup:
        CloseableHttpClient httpClient = Mock(CloseableHttpClient)
        service = new HttpRequestService(httpClient)
        String baseUrl = "http://localhost:8080/"
        Long clientId = 3

        when:
        service.sendRequest(baseUrl, clientId)

        then:
        1 * httpClient.execute({ HttpGet httpGet ->
            httpGet.getURI().toString() == "${baseUrl}?clientId=$clientId"
        })
    }

    def 'Fail to send request'() {
        setup:
        CloseableHttpClient httpClient = Mock(CloseableHttpClient) {
            1 * execute(_) >> { throw new Exception() }
        }

        service = new HttpRequestService(httpClient)
        String baseUrl = "http://localhost:8080/"
        Long clientId = 3

        when:
        def response = service.sendRequest(baseUrl, clientId)

        then:
        response == null

    }
}
