package com.rivermeadow.java.springboot.demo.service;

import com.rivermeadow.java.springboot.demo.model.TargetCloud;
import com.rivermeadow.java.springboot.demo.model.Workload;
import com.rivermeadow.java.springboot.demo.repository.TargetRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by John Morning on 3/6/2020.
 */
@Service
public class TargetService {

    Logger logger = LoggerFactory.getLogger(TargetService.class);

    private final TargetRepository targetRepository;

    @Autowired
    public TargetService(TargetRepository targetRepository) {
        logger.trace("In TargetService Constructor");
        this.targetRepository = targetRepository;
    }

    public TargetCloud addTarget(TargetCloud target) {
        logger.trace("TargetService.addTarget(" + target + ")");
        return targetRepository.save(target);
    }
    
    public List<TargetCloud> getAllTarget() {
        logger.trace("TargetService.getAllTarget()");
        return (List<TargetCloud>) targetRepository.findAll();
    }
    
    public Optional<TargetCloud> getTargetById(UUID id) {
        logger.trace("TargetService.getTargetById(" + id + ")");
        return targetRepository.findById(id);
    }
    
    public void deleteTarget(UUID id) {
        logger.trace("TargetService.deleteTarget(" + id + ")");
        targetRepository.deleteById(id);
    }
    
    public TargetCloud updateTarget(UUID id, TargetCloud target) {
        logger.trace("TargetService.updateTarget(" + target + ")");

        Optional<TargetCloud> optTargetCloud = targetRepository.findById(id);
        if (optTargetCloud != null && optTargetCloud.isPresent()) {
            logger.trace("TargetService.updateTarget() found targetCloud with ID - " + id + " to update");
            TargetCloud targetCloud = optTargetCloud.get();
            // cannot change Primary Key
            target.setId(targetCloud.getId());
            // cannot change IPAddress
            target.setIpAddress(targetCloud.getIpAddress());
            logger.trace("TargetService.updateSource() updating target with " + target );
        } else {
            logger.trace("TargetService.updateSource() saving new target with " + target + " update did not previously exist" );
        }

        return targetRepository.save(target);
    }

    public void saveAllTargets(List<TargetCloud> targets) {
        logger.trace("TargetService.saveAllTargets(" + targets + ")");
        targetRepository.saveAll(targets);
    }
}
