package com.diablo3CharViewer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class FetchToken {

    //make const and add to git ignore
    String clientId = ClientCredentials.getClientId(); //"your_client_id";
    String clientSecret = ClientCredentials.getClientSecret(); //"your_client_secret";

    // Combine client_id and client_secret into base64-encoded string
    String credentials = clientId + ":" + clientSecret;
    String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

    // Battle.net token endpoint
    String tokenUrl = "https://oauth.battle.net/token";

    // Prepare the request payload
    String requestBody = "grant_type=client_credentials";

    // Create the HTTP client
    HttpClient httpClient = HttpClient.newHttpClient();

    public Token requestToken() {

        // Create the HTTP request and fetch token
        String json = initializeApiServicePOST();

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

    private String initializeApiServicePOST() {

        try {
            // Create the HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(tokenUrl))
                    .header("Authorization", "Basic " + base64Credentials)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() != 200) {
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

    public String initializeApiServiceGET(String battleNETURL) {

        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(battleNETURL))
                    .GET()
                    .setHeader("Authorization", "Bearer " + requestToken().getAccess_token())
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() != 200) {
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

}
