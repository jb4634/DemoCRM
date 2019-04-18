package com.podjetje.democrm.repository;

import com.podjetje.democrm.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer>  findCustomersByFirstName(String firstName);
    List<Customer>  findCustomersByLastName(String lastName);
    List<Customer>  findCustomersByFirstNameAndLastName(String firstName, String lastName);
}
