package com.podjetje.democrm.service.impl;

import com.podjetje.democrm.entity.Customer;
import com.podjetje.democrm.repository.CustomerRepository;
import com.podjetje.democrm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    // Creates new Customer in the database created from the given parameters
    @Override
    public void saveCustomerFields(String firstName, String lastName, String email, String phone) {
        Customer customer = new Customer(firstName,lastName,email,phone);
        saveCustomer(customer);
    }

    // Creates new Customer in the database
    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    // Deletes Customer from the database
    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    // Deletes Customer with given 'customerId' from the database
    @Override
    public void deleteCustomerById(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

    // Updates Customer in the database
    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    // Returns all the Customers from the database
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Returns customers filtered by first name and/or last name
    @Override
    public List<Customer> getCustomersByName(String firstName, String lastName) {
        if (StringUtils.isEmpty(firstName)) {
            if (StringUtils.isEmpty(lastName)){
                return getAllCustomers(); // returns all of the Customers if both arguments are null
            }
            else{
                return customerRepository.findCustomersByLastName(lastName); // Filters by last name
            }
        } else if (StringUtils.isEmpty(lastName)){
            return customerRepository.findCustomersByFirstName(firstName); // Filters by first name
        }else{
            // Filters by first and last name
            return customerRepository.findCustomersByFirstNameAndLastName(firstName, lastName);
        }
    }

    // Returns the Customer with the given id
    @Override
    public Customer getCustomerById(Integer id) {
        return customerRepository.findCustomerById(id);
    }
}
