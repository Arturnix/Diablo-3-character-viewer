package com.diablo3CharViewer.token;

import com.diablo3CharViewer.ClientCredentials;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class FetchToken {

    //make const and add to git ignore
    private final String clientId = ClientCredentials.getClientId(); //"your_client_id";
    private final String clientSecret = ClientCredentials.getClientSecret(); //"your_client_secret";

    // Combine client_id and client_secret into base64-encoded string
    private final String credentials = clientId + ":" + clientSecret;
    private final String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

    // Battle.net token endpoint
    private final String tokenUrl = "https://oauth.battle.net/token";

    // Prepare the request payload
    private final String requestBody = "grant_type=client_credentials";

    // Create the HTTP client
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public Token requestToken() {

        // Create the HTTP request and fetch token
        String json = fetchTokenRequest();

        ObjectMapper objectMapper = new ObjectMapper();
        Token token = null;

        try {
            token = objectMapper.readValue(json, Token.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return token;
    }

    private String fetchTokenRequest() {

        try {
            // Create the HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(tokenUrl))
                    .header("Authorization", "Basic " + base64Credentials)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("HttpResponseCode: " + response.statusCode());
            } else {
                return response.body();
            }

        } catch (InterruptedException e) {
            System.out.println("Processing interrupted!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error occurred!");
            e.printStackTrace();
        }
        return "";
    }

    public String fetchAPIResourceRequest(String battleNETURL) {

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(battleNETURL))
                    .GET()
                    .setHeader("Authorization", "Bearer " + requestToken().getAccess_token())
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() != HttpURLConnection.HTTP_OK) {
               //return "Gracz o podanym battleTagu nie istnieje w swiecie Sanktuarium"; //System.out.println("Gracz o podanym battleTagu nie istnieje w swiecie Sanktuarium!");
                throw new RuntimeException("Szukany twor nie istnieje w swiecie Sanktuarium!\nPodaj poprawna nazwe.\n" + "HttpResponseCode: " + response.statusCode());
                //throw new RuntimeException("Gracz o podanym battleTagu nie istnieje w swiecie Sanktuarium!");
            } else {
                return response.body();
            }

        } catch (InterruptedException e) {
            System.out.println("Processing interrupted!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error occurred!");
            e.printStackTrace();
        }
        return "";
    }
}