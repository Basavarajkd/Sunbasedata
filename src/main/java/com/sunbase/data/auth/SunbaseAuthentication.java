package com.sunbase.data.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SunbaseAuthentication {

    @Value("${sunbase.auth.url}")
    private String authUrl; // URL for the authentication API

    @Value("${sunbase.auth.login_id}")
    private String loginId; // Your login ID

    @Value("${sunbase.auth.password}")
    private String password; // Your password

    private String bearerToken; // Store the bearer token

    private final RestTemplate restTemplate;

    public SunbaseAuthentication() {
        this.restTemplate = new RestTemplate();
    }

    public String authenticate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Prepare the request body with login credentials
        String requestBody = "{\"login_id\":\"" + loginId + "\",\"password\":\"" + password + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send the POST request to the authentication API
        ResponseEntity<AuthenticationResponse> responseEntity = restTemplate.exchange(authUrl, HttpMethod.POST, requestEntity, AuthenticationResponse.class);
        AuthenticationResponse response = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();

        // Extract the bearer token from the response
        if (statusCode == HttpStatus.OK && response != null) {
            // Set the bearer token obtained from the API response
            this.bearerToken = response.getToken();
            return this.bearerToken;
        } else {
            // Handle authentication failure, throw an exception, or take appropriate action
            throw new RuntimeException("Authentication failed. Unable to obtain bearer token.");
        }
    }

    public String getBearerToken() {
        return bearerToken;
    }
}
