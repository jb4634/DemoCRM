package com.podjetje.democrm.service;

import com.podjetje.democrm.entity.Customer;
import com.podjetje.democrm.entity.Meeting;

import java.util.List;

public interface MeetingService {
    void saveMeeting(Meeting meeting);
    void updateMeeting(Meeting meeting);
    void deleteMeeting(Meeting meeting);
    List<Meeting> getAllMeetings();
    List<Meeting> getMeetingByCustomer(Customer customer);
}
