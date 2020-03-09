package com.rivermeadow.java.springboot.demo.service;

import com.rivermeadow.java.springboot.demo.model.Migration;
import com.rivermeadow.java.springboot.demo.model.MigrationState;
import com.rivermeadow.java.springboot.demo.repository.MigrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by John Morning on 3/6/2020.
 */
@Service
public class MigrationService {

    Logger logger = LoggerFactory.getLogger(MigrationService.class);

    private final MigrationRepository migrationRepository;

    @Autowired
    public MigrationService(MigrationRepository migrationRepository) {
        logger.trace("In MigrationService Constructor");
        this.migrationRepository = migrationRepository;
    }

    public Migration addMigration(Migration migration) {
        logger.trace("MigrationService.addMigration(" + migration + ")");
        return migrationRepository.save(migration);
    }
    
    public List<Migration> getAllMigration() {
        logger.trace("MigrationService.getAllMigration()");
        return (List<Migration>) migrationRepository.findAll();
    }

    public Optional<Migration> getMigrationById(UUID id) {
        logger.trace("MigrationService.getMigrationById(" + id + ")");
        return migrationRepository.findById(id);
    }

    public MigrationState getMigrationStateById(UUID id) {
        logger.trace("MigrationService.getMigrationStateById(" + id + ")");
        Optional<Migration> optMigration = migrationRepository.findById(id);
        if (optMigration != null && optMigration.isPresent()) {
            logger.trace("MigrationService.getMigrationStateById() found migration with ID - " + id);
            Migration migration = optMigration.get();
            return migration.getState();
        } else {
            logger.trace("MigrationService.getMigrationStateById() did NOT find migration with ID - " + id);
            return null;
        }
    }

    public void deleteMigration(UUID id) {
        logger.trace("MigrationService.deleteMigration(" + id + ")");
        migrationRepository.deleteById(id);
    }
    
    public Migration updateMigration(UUID id, Migration migration) {
        logger.trace("MigrationService.updateMigration(" + migration + ")");

        Optional<Migration> optMigration = migrationRepository.findById(id);
        if (optMigration != null && optMigration.isPresent()) {
            logger.trace("MigrationService.updateMigration() found migration with ID - " + id + " to update");
            Migration runningJob = optMigration.get();
            // cannot change Primary Key
            migration.setId(runningJob.getId());
            // cannot change IPAddress
            migration.setIpAddress(runningJob.getIpAddress());
            logger.trace("MigrationService.updateSource() updating migration with " + migration );
        } else {
            logger.trace("MigrationService.updateSource() saving new migration with " + migration + " update did not previously exist" );
        }

        return migrationRepository.save(migration);
    }

    public void saveAllMigrations(List<Migration> migrations) {
        logger.trace("MigrationService.saveAllMigrations(" + migrations + ")");
        migrationRepository.saveAll(migrations);
    }
}
