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

    @Override
    public void saveCustomerFields(String firstName, String lastName, String email, String phone) {
        Customer customer = new Customer(firstName,lastName,email,phone);
        saveCustomer(customer);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> getCustomersByName(String firstName, String lastName) {
        if (StringUtils.isEmpty(firstName)) {
            if (StringUtils.isEmpty(lastName)){
                return getAllCustomers();
            }
            else{
                return customerRepository.findCustomersByLastName(lastName);
            }
        } else if (StringUtils.isEmpty(lastName)){
            return customerRepository.findCustomersByFirstName(firstName);
        }else{
            return customerRepository.findCustomersByFirstNameAndLastName(firstName, lastName);
        }
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerRepository.findCustomerById(id);
    }
}
