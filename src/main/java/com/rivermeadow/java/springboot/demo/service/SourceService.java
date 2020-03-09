package com.rivermeadow.java.springboot.demo.service;

import com.rivermeadow.java.springboot.demo.repository.SourceRepository;
import com.rivermeadow.java.springboot.demo.model.Workload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by John Morning on 3/6/2020.
 */
@Service
public class SourceService {

    Logger logger = LoggerFactory.getLogger(SourceService.class);

    private final SourceRepository sourceRepository;

    @Autowired
    public SourceService(SourceRepository sourceRepository) {
        logger.trace("In SourceService Constructor");
        this.sourceRepository = sourceRepository;
    }

    public Workload addSource(Workload source) {
        logger.trace("SourceService.addSource(" + source + ")");
        return sourceRepository.save(source);
    }
    
    public List<Workload> getAllSource() {
        logger.trace("SourceService.getAllSource()");
        return (List<Workload>) sourceRepository.findAll();
    }
    
    public Optional<Workload> getSourceById(UUID id) {
        logger.trace("SourceService.getSourceById(" + id + ")");
        return sourceRepository.findById(id);
    }

    public void deleteSource(UUID id) {
        logger.trace("SourceService.deleteSource(" + id + ")");
        sourceRepository.deleteById(id);
    }
    
    public Workload updateSource(UUID id, Workload source) {
        logger.trace("SourceService.updateSource(" + id + ", " + source + ")");
        Optional<Workload> optWorkload = sourceRepository.findById(id);
        if (optWorkload != null && optWorkload.isPresent()) {
            logger.trace("SourceService.updateSource() found workload with ID - " + id + " to update");
            Workload workload = optWorkload.get();
            // cannot change Primary Key
            source.setId(workload.getId());
            // cannot change IPAddress
            source.setIpAddress(workload.getIpAddress());
            logger.trace("SourceService.updateSource() updating source with " + source );
        } else {
            logger.trace("SourceService.updateSource() saving source with " + source + " update did not previously exist" );
        }

        return sourceRepository.save(source);
    }

    public void saveAllSources(List<Workload> sources) {
        logger.trace("TargetService.saveAllSources(" + sources + ")");
        sourceRepository.saveAll(sources);
    }
}
