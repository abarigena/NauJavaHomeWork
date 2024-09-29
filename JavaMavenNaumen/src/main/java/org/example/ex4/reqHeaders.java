package org.example.ex4;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class reqHeaders {
    public static void main(String[] args) throws IOException, InterruptedException {

        try(HttpClient client = HttpClient.newHttpClient())
        {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/headers"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            HeadersResp headersResp = objectMapper.readValue(response.body(), HeadersResp.class);



            System.out.println("Заголовки запроса "+ headersResp.getHeaders().values());



        }
    }
}
