package com.podjetje.democrm.service.impl;

import com.podjetje.democrm.entity.Conclusion;
import com.podjetje.democrm.entity.Meeting;
import com.podjetje.democrm.repository.ConclusionRepository;
import com.podjetje.democrm.service.ConclusionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConclusionServiceImpl implements ConclusionService {

    @Autowired
    ConclusionRepository conclusionRepository;

    // Creates new Conclusion in the database
    @Override
    public void saveConclusion(Conclusion conclusion) {
        conclusionRepository.save(conclusion);
    }

    // Updates the Conlcusion in the database
    @Override
    public void updateConclusion(Conclusion conclusion) {
        conclusionRepository.save(conclusion);
    }

    // Deletes the Conclusion from the database
    @Override
    public void deleteConclusion(Conclusion conclusion) {
        conclusionRepository.delete(conclusion);
    }

    // Returns all of the Conclusions from the database
    @Override
    public List<Conclusion> getAllConclusions() {
        return conclusionRepository.findAll();
    }

    // Returns the Conclusions made at the given Meeting from the database
    @Override
    public List<Conclusion> getConclusionsByMeeting(Meeting meeting) {
        return conclusionRepository.findConclusionsByMeeting(meeting);
    }
}
