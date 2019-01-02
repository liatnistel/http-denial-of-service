import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class clientRunner {

    private Map<Long, RequestClient> runningClients = new HashMap<>();
    ExecutorService executor = Executors.newCachedThreadPool();

    public void startClients(int numberOfClients) {
        for (long i = 0; i < numberOfClients; i++) {
            RequestClient client = new RequestClient(i);
            runningClients.put(i, client);
            executor.execute(client);
        }
    }

    public void shutDownClients() {
        runningClients.forEach((key, value) -> {
            value.shutDownClient();
        });

        try {
            if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }


}

