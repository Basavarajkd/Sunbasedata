package com.sunbase.data.controller;

import com.sunbase.data.auth.SunbaseAuthentication;
import com.sunbase.data.payload.CustomerDTO;
import com.sunbase.data.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final SunbaseAuthentication sunbaseAuthentication; // Inject SunbaseAuthentication

    @Autowired
    public CustomerController(CustomerService customerService, SunbaseAuthentication sunbaseAuthentication) {
        this.customerService = customerService;
        this.sunbaseAuthentication = sunbaseAuthentication;
    }


    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDto) {
        // Get the bearer token from SunbaseAuthentication and set it in the request headers
        String bearerToken = sunbaseAuthentication.getBearerToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + bearerToken);

        CustomerDTO createdCustomer = customerService.createCustomer(customerDto);
        return ResponseEntity.ok().headers(headers).body(createdCustomer);
    }

    @GetMapping("/customer_list") // Handle request for customer list page
    public String getCustomerListPage() {
        return "customer_list.html"; // Return the name of the HTML file
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getCustomerList() {
        // Get the bearer token from SunbaseAuthentication and set it in the request headers
        String bearerToken = sunbaseAuthentication.getBearerToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + bearerToken);

        List<CustomerDTO> customers = customerService.getCustomerList();
        return ResponseEntity.ok().headers(headers).body(customers);
    }

    @PostMapping("/{customerId}/delete")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID customerId) {
        // Get the bearer token from SunbaseAuthentication and set it in the request headers
        String bearerToken = sunbaseAuthentication.getBearerToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + bearerToken);

        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok().headers(headers).build();
    }

    @PostMapping("/{customerId}/update")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable UUID customerId, @RequestBody CustomerDTO customerDto) {
        // Get the bearer token from SunbaseAuthentication and set it in the request headers
        String bearerToken = sunbaseAuthentication.getBearerToken();
        HttpHeaders headers = new HttpHeaders();
        if (bearerToken != null) {
            headers.set("Authorization", "Bearer " + bearerToken);
        } else {
            // Handle authentication failure or missing bearer token
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Check if customerId and customerDto are not null before making the update request
        if (customerId == null || customerDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        CustomerDTO updatedCustomer = customerService.updateCustomer(customerId, customerDto);
        if (updatedCustomer != null) {
            return ResponseEntity.ok().headers(headers).body(updatedCustomer);
        } else {
            return ResponseEntity.notFound().headers(headers).build();
        }
    }
}
