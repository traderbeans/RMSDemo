package com.rivermeadow.java.springboot.demo.api;

import com.rivermeadow.java.springboot.demo.model.*;
import com.rivermeadow.java.springboot.demo.service.TargetService;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by John Morning on 3/8/2020.
 */
@RequestMapping("api/v1/target")
@RestController
public class TargetController {

    Logger logger = LoggerFactory.getLogger(TargetController.class);

    private TargetService targetService;

    @Autowired
    public TargetController(TargetService targetService) {
        this.targetService = targetService;
        logger.trace("In TargetController constructor");
    }

    @PostMapping
    public void addTarget(@Valid @NotNull @RequestBody TargetCloud target) {
        logger.trace("TargetController.addTarget(" + target + ")");
        targetService.addTarget(target);
    }
    
    @GetMapping
    public List<TargetCloud> getAllTarget() {
        logger.trace("TargetController.getAllTarget()");
        return targetService.getAllTarget();
    }
    
    @GetMapping(path = "{id}")
    public TargetCloud getTargetById(@PathVariable("id") UUID id) {
        logger.trace("TargetController.getTargetById(" + id + ")");
    	return targetService.getTargetById(id)
    			.orElse( null );
    }

    @DeleteMapping(path = "{id}")
    public void deleteTargetById(@PathVariable("id") UUID id) {
        logger.trace("TargetController.deleteTargetById(" + id + ")");
    	targetService.deleteTarget(id);
    }

    @PutMapping(path = "{id}")
    public void updateTarget(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody TargetCloud targetToUpdate) {
        logger.trace("SourceController.updateTarget(" + id + ", "+ targetToUpdate + ")");
    	targetService.updateTarget(id, targetToUpdate);
    }

    /**
     * Preload some sample data into the data store for validation testing purposes
     */
    @PostConstruct
    public void insertSampleTargetData() {

        // test target cloud, and work load target one
        String ipOne = "192.168.1.38";
        String usernameOne = "devUidOne";
        String passwordOne = "pwdDevOne";
        String domainOne = "dev.one.io";
        Map<String,Integer> storageOne = new HashMap<>();
        storageOne.put("c:", 250);
        storageOne.put("f:", 260);
        storageOne.put("h:", 270);
        Workload targetWorkloadOne = new Workload(ipOne, usernameOne, passwordOne, domainOne, storageOne);
        CloudType cloudTypeOne = CloudType.AWS;
        String targetUsernameOne = "sitUidOne";
        String targetPasswordOne = "pwdSitOne";
        String targetDomainOne = "sit.one.io";
        TargetCloud targetCloudOne = new TargetCloud(cloudTypeOne, usernameOne, passwordOne, domainOne, ipOne, targetUsernameOne, targetPasswordOne, targetDomainOne, storageOne);

        // test target cloud, and work load target two
        String ipTwo = "192.168.1.41";
        String usernameTwo = "devUidTwo";
        String passwordTwo = "pwdDevTwo";
        String domainTwo = "dev.two.io";
        Map<String,Integer> storageTwo = new HashMap<>();
        storageTwo.put("c:", 250);
        storageTwo.put("f:", 260);
        storageTwo.put("h:", 270);
        Workload targetWorkloadTwo = new Workload(ipTwo, usernameTwo, passwordTwo, domainTwo, storageTwo);
        CloudType cloudTypeTwo = CloudType.AWS;
        String targetUsernameTwo = "sitUidTwo";
        String targetPasswordTwo = "pwdSitTwo";
        String targetDomainTwo = "sit.two.io";
        TargetCloud targetCloudTwo = new TargetCloud(cloudTypeTwo, usernameTwo, passwordTwo, domainTwo, ipTwo, targetUsernameTwo, targetPasswordTwo, targetDomainTwo, storageTwo);

        List<TargetCloud> targetClouds = new ArrayList<>();
        targetClouds.add(targetCloudOne);
        targetClouds.add(targetCloudTwo);

        targetService.saveAllTargets(targetClouds);
    }
}
