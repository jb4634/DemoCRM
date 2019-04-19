package com.podjetje.democrm.repository;

import com.podjetje.democrm.entity.Conclusion;
import com.podjetje.democrm.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConclusionRepository extends JpaRepository<Conclusion, Integer> {

    // Lists only conclusion made at the given 'meeting'
    List<Conclusion> findConclusionsByMeeting(Meeting meeting);
}
