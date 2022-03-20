import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import static java.net.http.HttpClient.Version.HTTP_2;

public class HttpClientAsync {
    public static void main(String[] args) throws Exception {
        HttpClient httpClient =
                HttpClient.newBuilder()
                    .version(HTTP_2)
                    .build();

        HttpRequest req = HttpRequest.newBuilder(URI.create("https://google.com"))
                            .GET()
                            .build();


        CompletableFuture<HttpResponse<String>> resFuture =
                httpClient.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        resFuture.thenAccept(res -> System.out.println(res.version()));
        resFuture.join();
    }
}
