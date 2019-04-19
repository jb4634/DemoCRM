package com.podjetje.democrm.repository;

import com.podjetje.democrm.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer>  findCustomersByFirstName(String firstName); // Filters Customers by first name
    List<Customer>  findCustomersByLastName(String lastName); // Filters Customers by last name

    // Filters Customers by first and last name
    List<Customer>  findCustomersByFirstNameAndLastName(String firstName, String lastName);

    // Finds Customer by id
    Customer findCustomerById(Integer id);
}
