package com.podjetje.democrm.repository;

import com.podjetje.democrm.entity.Meeting;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeetingRepository extends CrudRepository<Meeting, Integer> {

    List<Meeting>  findByCustomer_FirstName(String firstName);
}
