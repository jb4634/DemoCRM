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

    @Override
    public void saveMeeting(Meeting meeting) {
        meetingRepository.save(meeting);
    }

    @Override
    public void updateMeeting(Meeting meeting) {
        meetingRepository.save(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetingRepository.delete(meeting);
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    @Override
    public List<Meeting> getMeetingByCustomer(Customer customer) {
        return meetingRepository.findByCustomer(customer);
    }

    @Override
    public Meeting getMeetingById(Integer id) {
        return meetingRepository.findMeetingById(id);
    }
}
