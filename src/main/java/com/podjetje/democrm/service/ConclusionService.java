package com.podjetje.democrm.service;

import com.podjetje.democrm.entity.Conclusion;
import com.podjetje.democrm.entity.Meeting;

import java.util.List;

public interface ConclusionService {
    void saveConclusion(Conclusion conclusion);
    void updateConclusion(Conclusion conclusion);
    void deleteConclusion(Conclusion conclusion);
    List<Conclusion> getAllConclusions();
    List<Conclusion> getConclusionsByMeeting(Meeting meeting);

}
