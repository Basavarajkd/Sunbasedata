package com.sunbase.data.service;


import com.sunbase.data.payload.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDto);

    List<CustomerDTO> getCustomerList();

    void deleteCustomer(UUID customerId);

    CustomerDTO updateCustomer(UUID customerId, CustomerDTO customerDto);
}

