package com.rivermeadow.java.springboot.demo.jobs;

import com.rivermeadow.java.springboot.demo.model.Migration;
import com.rivermeadow.java.springboot.demo.model.MigrationState;
import com.rivermeadow.java.springboot.demo.service.MigrationService;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by John Morning on 3/8/2020.
 */
public class MigrationRunner implements Runnable {

    Logger logger = LoggerFactory.getLogger(MigrationRunner.class);

    private MigrationService migrationService;
    private UUID migrationId;
    private Migration migration;

    public MigrationRunner(MigrationService migrationService, UUID migrationId) {
        logger.trace("In MigrationRunner constructor");
        this.migrationService = migrationService;
        this.migrationId = migrationId;
    }

    /**
     * Implement run method:
     * - run method should sleep for 5 min (simulate running migration)
     * - copy source object to the target field of TargetCloud and target should only have volumes that are selected in the migration.
     *   For example, if source has: C:\ D:\ and E:\ and only C: was selected, target should only have C:\
     *   Implement business logic to not allow running migrations when volume C:\ is not included in the selected mount points list
     */
    @Override
    public void run() {

        migration = getMigration();

        if (migration != null && migration.getState().equals(MigrationState.NOT_STARTED)) {
            logger.trace("MigrationRunner found Migration with 'NOT_STARTED' state");
            setMigrationState(MigrationState.RUNNING);
            try {
                // sleep for 5 minutes ( 5 minutes x 60 seconds/minute x 1000 milliseconds )
                Thread.sleep(300000);
                logger.trace("MigrationRunner slept 5 minutes");
                //TODO copy source to the target, implementing business logic above
                setMigrationState(MigrationState.SUCCESS);
            } catch (InterruptedException iEx) {
                setMigrationState(MigrationState.ERROR);
                logger.error(iEx.getMessage());
                //iEx.printStackTrace();
            }
        } else {
            logger.trace("MigrationRunner found Migration State NOT equal to 'NOT_STARTED', ignoring request");
        }
    }

    /**
     * update the migration state in the data store
     */
    public synchronized void setMigrationState(MigrationState state) {
        logger.trace("MigrationRunner " + migrationId + " updating state to " + state);
        migration.setState(state);
        migrationService.updateMigration(migrationId, migration);
    }

    /**
     * get the migration associated with this UUID
     */
    public synchronized Migration getMigration() {
        logger.trace("MigrationRunner.getMigrationById(" + migrationId + ")");
        Optional<Migration> optMigration = migrationService.getMigrationById(migrationId);
        if (optMigration != null && optMigration.isPresent()) {
            logger.trace("MigrationRunner.getMigrationById() found migration with ID - " + migrationId);
            Migration migration = optMigration.get();
            return migration;
        } else {
            logger.trace("MigrationRunner.getMigrationById() did NOT find migration with ID - " + migrationId);
            return null;
        }
    }
}
