package com.rivermeadow.java.springboot.demo.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rivermeadow.java.springboot.demo.jobs.MigrationRunner;
import com.rivermeadow.java.springboot.demo.model.*;
import com.rivermeadow.java.springboot.demo.service.MigrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by John Morning on 3/8/2020.
 */
@RequestMapping("api/v1/migration")
@RestController
public class MigrationController {

    Logger logger = LoggerFactory.getLogger(MigrationController.class);

    private MigrationService migrationService;

    @Autowired
    public MigrationController(MigrationService migrationService) {
        this.migrationService = migrationService;
        logger.trace("In MigrationController constructor");
    }

    @PostMapping
    public void addMigration(@Valid @NotNull @RequestBody Migration migration) {
        logger.trace("MigrationController.addMigration(" + migration + ")");
        migrationService.addMigration(migration);
    }
    
    @GetMapping
    public List<Migration> getAllMigration() {
        logger.trace("MigrationController.getAllMigration()");
        return migrationService.getAllMigration();
    }
    
    @GetMapping(path = "{id}")
    public Migration getMigrationById(@PathVariable("id") UUID id) {
        logger.trace("MigrationController.getMigrationById(" + id + ")");
    	return migrationService.getMigrationById(id)
    			.orElse( null );
    }

    @GetMapping(path = "/state/{id}")
    public MigrationState getMigrationStateById(@PathVariable("id") UUID id) {
        logger.trace("MigrationController.getMigrationStateById(" + id + ")");
        return migrationService.getMigrationStateById(id);
    }

    @GetMapping(path = "/start/{id}")
    public void startMigrationById(@PathVariable("id") UUID id) {
        logger.trace("MigrationController.startMigrationById(" + id + ")");
        MigrationRunner migrationRunner = new MigrationRunner(migrationService, id);
        Thread migrationThread = new Thread(migrationRunner);
        migrationThread.start();
    }

    @DeleteMapping(path = "{id}")
    public void deleteMigrationById(@PathVariable("id") UUID id) {
        logger.trace("MigrationController.deleteMigrationById(" + id + ")");
    	migrationService.deleteMigration(id);
    }

    @PutMapping(path = "{id}")
    public void updateMigration(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Migration migrationToUpdate) {
        logger.trace("SourceController.updateMigration(" + id + ", "+ migrationToUpdate + ")");
    	migrationService.updateMigration(id, migrationToUpdate);
    }

    /**
     * Preload some sample data into the data store for validation testing purposes
     */
    @PostConstruct
    public void insertSampleMigrationData() {

        // test target cloud, and work load target one
        String sourceIpAddressOne = "192.168.1.38";
        String sourceUsernameOne = "devUidOne";
        String sourcePasswordOne = "pwdDevOne";
        String sourceDomainOne = "dev.one.io";
        Map<String,Integer> sourceStorageOne = new HashMap<>();
        sourceStorageOne.put("c:", 250);
        sourceStorageOne.put("f:", 260);
        sourceStorageOne.put("h:", 270);
        Workload sourceWorkloadOne = new Workload(sourceIpAddressOne, sourceUsernameOne, sourcePasswordOne, sourceDomainOne, sourceStorageOne);

        String ipAddressOne = "172.68.17.138";
        String usernameOne = "prodUidOne";
        String passwordOne = "pwdProdOne";
        String domainOne = "prod.one.io";
        Map<String,Integer> storageOne = new HashMap<>();
        storageOne.put("c:", 650);
        storageOne.put("f:", 660);
        storageOne.put("h:", 670);
        Workload targetWorkloadOne = new Workload(ipAddressOne, usernameOne, passwordOne, domainOne, storageOne);

        CloudType cloudTypeOne = CloudType.AWS;
        String cloudUsernameOne = "sitUidOne";
        String cloudPasswordOne = "pwdSitOne";
        String cloudDomainOne = "sit.one.io";
        TargetCloud targetCloudOne = new TargetCloud(cloudTypeOne, usernameOne, passwordOne, domainOne, ipAddressOne, cloudUsernameOne, cloudPasswordOne, cloudDomainOne, storageOne);

        List<String> mountPointsOne = new ArrayList<>();
        mountPointsOne.add("/export0");
        mountPointsOne.add("/export1");
        mountPointsOne.add("/export2");

        // test target cloud, and work load target two
        String sourceIpAddressTwo = "192.168.1.41";
        String sourceUsernameTwo = "devUidTwo";
        String sourcePasswordTwo = "pwdDevTwo";
        String sourceDomainTwo = "dev.two.io";
        Map<String,Integer> sourceStorageTwo = new HashMap<>();
        sourceStorageTwo.put("c:", 250);
        sourceStorageTwo.put("f:", 260);
        sourceStorageTwo.put("h:", 270);
        Workload sourceWorkloadTwo = new Workload(sourceIpAddressTwo, sourceUsernameTwo, sourcePasswordTwo, sourceDomainTwo, sourceStorageTwo);

        String ipAddressTwo = "172.68.17.141";
        String usernameTwo = "prodUidTwo";
        String passwordTwo = "pwdProdTwo";
        String domainTwo = "prod.two.io";
        Map<String,Integer> storageTwo = new HashMap<>();
        storageTwo.put("c:", 350);
        storageTwo.put("f:", 360);
        storageTwo.put("h:", 370);
        Workload targetWorkloadTwo = new Workload(ipAddressTwo, usernameTwo, passwordTwo, domainTwo, storageTwo);

        CloudType cloudTypeTwo = CloudType.AWS;
        String cloudUsernameTwo = "sitUidTwo";
        String cloudPasswordTwo = "pwdSitTwo";
        String cloudDomainTwo = "sit.two.io";
        TargetCloud targetCloudTwo = new TargetCloud(cloudTypeTwo, usernameTwo, passwordTwo, domainTwo, ipAddressTwo, cloudUsernameTwo, cloudPasswordTwo, cloudDomainTwo, storageTwo);

        List<String> mountPointsTwo = new ArrayList<>();
        mountPointsTwo.add("/export0");
        mountPointsTwo.add("/export1");
        mountPointsTwo.add("/export2");

        Migration migrationOne = new Migration(
                mountPointsOne,
                sourceIpAddressOne,
                sourceUsernameOne,
                sourcePasswordOne,
                sourceDomainOne,
                sourceStorageOne,
                cloudTypeOne,
                cloudUsernameOne,
                cloudPasswordOne,
                cloudDomainOne,
                ipAddressOne,
                usernameOne,
                passwordOne,
                domainOne,
                storageOne
        );
        Migration migrationTwo = new Migration(
                mountPointsTwo,
                sourceIpAddressTwo,
                sourceUsernameTwo,
                sourcePasswordTwo,
                sourceDomainTwo,
                sourceStorageTwo,
                cloudTypeTwo,
                cloudUsernameTwo,
                cloudPasswordTwo,
                cloudDomainTwo,
                ipAddressTwo,
                usernameTwo,
                passwordTwo,
                domainTwo,
                storageTwo
        );

        List<Migration> migrations = new ArrayList<>();
        migrations.add(migrationOne);
        migrations.add(migrationTwo);

        migrationService.saveAllMigrations(migrations);
    }
}
