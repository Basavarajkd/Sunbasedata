package com.sunbase.data.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @PostMapping("/sunbase/portal/api/assignment_auth.jsp")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        String loginId = request.getLogin_id();
        String password = request.getPassword();

        // Your authentication logic here
        if ("test@sunbasedata.com".equals(loginId) && "Test@123".equals(password)) {
            // Authentication successful
            String token = generateBearerToken(loginId); // Generate a unique bearer token for the user
            AuthenticationResponse response = new AuthenticationResponse(token);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Allow-Origin", "http://localhost:8080");
            headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");
            headers.add("Access-Control-Allow-Credentials", "true");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(response);
        } else {
            // Authentication failed
            return ResponseEntity.status(401).build();
        }
    }

    // Sample method to generate a unique bearer token
    private String generateBearerToken(String loginId) {
        // Your token generation logic here (e.g., using UUID or JWT)
        return "dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=" + loginId;
    }
}

