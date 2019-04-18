package com.podjetje.democrm.service;

import com.podjetje.democrm.entity.Customer;

import java.util.List;

public interface CustomerService{

    void saveCustomerFields(String firstName, String lastName, String email, String phone);
    void saveCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    void deleteCustomerById(Integer customerId);
    void updateCustomer(Customer customer);
    List<Customer> getAllCustomers();
    List<Customer> getCustomersByName(String firstName, String lastName);
    Customer getCustomerById(Integer id);
}
