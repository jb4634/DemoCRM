package com.podjetje.democrm.repository;

import com.podjetje.democrm.entity.Customer;
import com.podjetje.democrm.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

    // Lists Meetings for given Customer and orders descending by date and timeStart
    List<Meeting> findByCustomerOrderByDateDescTimeStartDesc(Customer customer);

    // Finds Meeting by id
    Meeting findMeetingById(Integer id);
}
