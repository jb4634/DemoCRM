package com.podjetje.democrm.service.impl;

import com.podjetje.democrm.entity.ConclusionType;
import com.podjetje.democrm.repository.ConclusionTypeRepository;
import com.podjetje.democrm.service.ConclusionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConclusionTypeServiceImpl implements ConclusionTypeService {

    @Autowired
    ConclusionTypeRepository conclusionTypeRepository;

    // Returns all possible ConclusionTypes from the database
    @Override
    public List<ConclusionType> getAllConclusionTypes(){
        return conclusionTypeRepository.findAll();
    }

}
