package com.podjetje.democrm.repository;

import com.podjetje.democrm.entity.Customer;
import com.podjetje.democrm.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

    List<Meeting> findByCustomer_FirstName(String firstName);
    List<Meeting> findByCustomer(Customer customer);
    Meeting findMeetingById(Integer id);
}
