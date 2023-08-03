package com.sunbase.data.service;

import com.sunbase.data.entity.Customer;
import com.sunbase.data.payload.CustomerDTO;
import com.sunbase.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDto) {
        Customer customer = mapToEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return mapToDTO(savedCustomer);
    }

    @Override
    public List<CustomerDTO> getCustomerList() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerDTO updateCustomer(UUID customerId, CustomerDTO customerDto) {
        try {
            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            if (optionalCustomer.isPresent()) {
                Customer existingCustomer = optionalCustomer.get();
                mapToEntity(customerDto, existingCustomer);
                Customer updatedCustomer = customerRepository.save(existingCustomer);
                return mapToDTO(updatedCustomer);
            } else {
                return null;
            }
        } catch (NullPointerException ex) {
            // Handle or log the exception appropriately
            ex.printStackTrace();
            return null;
        }
    }

    // Mapping from Customer entity to CustomerDTO
    private CustomerDTO mapToDTO(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setStreet(customer.getStreet());
        customerDto.setAddress(customer.getAddress());
        customerDto.setCity(customer.getCity());
        customerDto.setState(customer.getState());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhone(customer.getPhone());
        return customerDto;
    }

    // Mapping from CustomerDTO to Customer entity
    private Customer mapToEntity(CustomerDTO customerDto) {
        if (customerDto == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setStreet(customerDto.getStreet());
        customer.setAddress(customerDto.getAddress());
        customer.setCity(customerDto.getCity());
        customer.setState(customerDto.getState());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        return customer;
    }

    // Mapping from CustomerDTO to existing Customer entity
    private void mapToEntity(CustomerDTO customerDto, Customer customer) {
        if (customerDto == null || customer == null) {
            return;
        }

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setStreet(customerDto.getStreet());
        customer.setAddress(customerDto.getAddress());
        customer.setCity(customerDto.getCity());
        customer.setState(customerDto.getState());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
    }
}
