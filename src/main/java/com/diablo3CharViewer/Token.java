package com.diablo3CharViewer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class Token {

    //make const and add to git ignore
    String clientId = "1754818f260847d3822b294f3f7d692d"; //"your_client_id";
    String clientSecret = "ilMznRmswcJ4fLMDBweYNBq9RC3URJLL"; //"your_client_secret";

    // Combine client_id and client_secret into base64-encoded string
    String credentials = clientId + ":" + clientSecret;
    String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

    // Battle.net token endpoint
    String tokenUrl = "https://oauth.battle.net/token";

    // Prepare the request payload
    String requestBody = "grant_type=client_credentials";

    String accessToken;

    // Create the HTTP client
    HttpClient httpClient = HttpClient.newHttpClient();

    public String requestToken() {

        // Create the HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(tokenUrl))
                .header("Authorization", "Basic " + base64Credentials)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        {
            try {

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if(response.statusCode() != 200) {
                    throw new RuntimeException("HttpResponseCode: " + response.statusCode());
                } else {
                    return response.body();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void useToken(String battleNETURL) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(battleNETURL))
                .GET()
                .setHeader("Authorization", "Bearer " + accessToken)
                .build();
    }
}
