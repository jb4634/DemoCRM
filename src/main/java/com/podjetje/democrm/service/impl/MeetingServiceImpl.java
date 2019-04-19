package com.podjetje.democrm.service.impl;

import com.podjetje.democrm.entity.Customer;
import com.podjetje.democrm.entity.Meeting;
import com.podjetje.democrm.repository.MeetingRepository;
import com.podjetje.democrm.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    MeetingRepository meetingRepository;

    // Creates new Meeting in the database
    @Override
    public void saveMeeting(Meeting meeting) {
        meetingRepository.save(meeting);
    }

    // Updates the Meeting in the database
    @Override
    public void updateMeeting(Meeting meeting) {
        meetingRepository.save(meeting);
    }

    // Deletes the Meeting in the database
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetingRepository.delete(meeting);
    }

    // Returns all of the Meetings in the database
    @Override
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    // Returns all the Meetings with given Customer
    @Override
    public List<Meeting> getMeetingByCustomer(Customer customer) {
        return meetingRepository.findByCustomerOrderByDateDescTimeStartDesc(customer);
    }

    // Returns the Meeting with the given 'id'
    @Override
    public Meeting getMeetingById(Integer id) {
        return meetingRepository.findMeetingById(id);
    }
}
