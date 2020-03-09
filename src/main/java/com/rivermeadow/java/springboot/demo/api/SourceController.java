package com.rivermeadow.java.springboot.demo.api;

import com.rivermeadow.java.springboot.demo.model.Workload;
import com.rivermeadow.java.springboot.demo.service.SourceService;

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
@RequestMapping("api/v1/source")
@RestController
public class SourceController {

    Logger logger = LoggerFactory.getLogger(SourceController.class);

    private SourceService sourceService;

    @Autowired
    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
        logger.trace("In SourceController constructor");
    }

//    @Autowired
//    public void setSourceService(SourceService sourceService) {
//        this.sourceService = sourceService;
//        logger.trace("SourceController.setSourceService()");
//    }

    /**
     * sample POST - http://localhost:8080/api/v1/source
     *
     * raw JSON payload
     *
     * {"ipAddress":"10.2.2.24",
     *  "username":"uidThree",
     *  "password":"pwdThree",
     *  "domain":"three.org",
     *  "storage":{"c:":340,"d:":350,"e:":360}}
     *
     */
    @PostMapping
    public void addSource(@Valid @NotNull @RequestBody Workload source) {
        logger.trace("SourceController.addSource(" + source + ")");
        sourceService.addSource(source);
    }

    /**
     * sample GET - http://localhost:8080/api/v1/source
     *
     */
    @GetMapping
    public List<Workload> getAllSource() {
        logger.trace("SourceController.getAllSource()");
        return sourceService.getAllSource();
    }

    /**
     * sample GET - http://localhost:8080/api/v1/source/519d5cf3-bd8a-4455-a159-824a9b43c154
     *
     */
    @GetMapping(path = "{id}")
    public Workload getSourceById(@PathVariable("id") UUID id) {
        logger.trace("SourceController.getSourceById(" + id + ")");
        return sourceService.getSourceById(id)
                .orElse( null );
    }

    /**
     * sample DELETE - http://localhost:8080/api/v1/source/519d5cf3-bd8a-4455-a159-824a9b43c154
     *
     */
    @DeleteMapping(path = "{id}")
    public void deleteSourceById(@PathVariable("id") UUID id) {
        logger.trace("SourceController.deleteSourceById(" + id + ")");
        sourceService.deleteSource(id);
    }

    /**
     * sample PUT - http://localhost:8080/api/v1/source/519d5cf3-bd8a-4455-a159-824a9b43c154
     *
     * raw JSON payload
     *
     * {"ipAddress":"10.2.2.24",
     *  "username":"uidThree",
     *  "password":"pwdThree",
     *  "domain":"three.org",
     *  "storage":{"c:":340,"d:":350,"e:":360}}
     *
     */
    @PutMapping(path = "{id}")
    public void updateSource(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Workload sourceToUpdate) {
        logger.trace("SourceController.updateSource(" + id + ", "+ sourceToUpdate + ")");
        sourceService.updateSource(id, sourceToUpdate);
    }

    /**
     * Preload some sample data into the data store for testing purposes
     */
    @PostConstruct
    public void insertSampleSourceData() {
        // test workload one
        String ipOne = "10.1.1.13";
        String usernameOne = "uidOne";
        String passwordOne = "pwdOne";
        String domainOne = "one.org";
        Map<String, Integer> storageOne = new HashMap<>();
        storageOne.put("c:", 110);
        storageOne.put("d:", 120);
        storageOne.put("e:", 130);
        Workload sourceOne = new Workload(ipOne, usernameOne, passwordOne, domainOne, storageOne);
        // test workload two
        String ipTwo = "10.1.1.14";
        String usernameTwo = "uidTwo";
        String passwordTwo = "pwdTwo";
        String domainTwo = "two.org";
        Map<String, Integer> storageTwo = new HashMap<>();
        storageTwo.put("c:", 140);
        storageTwo.put("d:", 150);
        storageTwo.put("e:", 160);
        Workload sourceTwo = new Workload(ipTwo, usernameTwo, passwordTwo, domainTwo, storageTwo);

        List<Workload> workloads = new ArrayList<>();
        workloads.add(sourceOne);
        workloads.add(sourceTwo);
        sourceService.saveAllSources(workloads);
    }
}
