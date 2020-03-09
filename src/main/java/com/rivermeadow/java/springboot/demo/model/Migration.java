package com.rivermeadow.java.springboot.demo.model;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rivermeadow.java.springboot.demo.model.MigrationState;
import com.rivermeadow.java.springboot.demo.model.TargetCloud;
import com.rivermeadow.java.springboot.demo.model.Workload;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by John Morning on 3/8/2020.
 */
@Table("migration")
public class Migration implements Serializable {

    @PrimaryKey
    @CassandraType(type = DataType.Name. UUID)
    private UUID id;
    private List<String> mountPoints;

//    private Workload source;
    // start of Workload source variables
    @NotNull
    private String sourceIpAddress;
    // Credentials
    @NotNull
    private String sourceUsername;
    @NotNull
    private String sourcePassword;
    private String sourceDomain;
    // Volume( mountPoint, totalSize )
    private Map<String, Integer> sourceStorage;
    // end of Workload

//    private TargetCloud target;
    // start of TargetCloud target variables
    private CloudType cloudType;
    // Cloud Credentials
    private String cloudUsername ;
    private String cloudPassword;
    private String cloudDomain;
    // target Workload
    private String ipAddress;
    // target Workload Credentials
    private String username;
    private String password;
    private String domain;
    // target Workload Volume( mountPoint, totalSize )
    private Map<String, Integer> storage;
    // end of TargetCloud

    private volatile MigrationState state; // four possible states: not started, running, error, success

    /**
     * Default Constructor - set UUID
     */
    public Migration() {
        this.id = UUID.randomUUID();
    }

    /**
     * Constructor
     *
     */
    public Migration(@JsonProperty("mountPoints") List<String> mountPoints,
                     @JsonProperty("sourceIpAddress") String sourceIpAddress,
                     @JsonProperty("sourceUsername") String sourceUsername,
                     @JsonProperty("sourcePassword") String sourcePassword,
                     @JsonProperty("sourceDomain") String sourceDomain,
                     @JsonProperty("sourceStorage") Map<String, Integer> sourceStorage,
                     @JsonProperty("cloudType") CloudType cloudType,
                     @JsonProperty("cloudUsername") String cloudUsername,
                     @JsonProperty("cloudPassword") String cloudPassword,
                     @JsonProperty("cloudDomain") String cloudDomain,
                     @JsonProperty("ipAddress") String ipAddress,
                     @JsonProperty("username") String username,
                     @JsonProperty("password") String password,
                     @JsonProperty("domain") String domain,
                     @JsonProperty("storage") Map<String, Integer> storage)
    {
        this();
        this.mountPoints = mountPoints;
        // workload
        this.sourceIpAddress = sourceIpAddress;
        this.sourceUsername = sourceUsername;
        this.sourcePassword = sourcePassword;
        this.sourceDomain = sourceDomain;
        this.sourceStorage = sourceStorage;
        // targetCloud
        this.cloudType = cloudType;
        this.cloudUsername = cloudUsername;
        this.cloudPassword = cloudPassword;
        this.cloudDomain = cloudDomain;
        this.ipAddress = ipAddress;
        this.username = username;
        this.password = password;
        this.domain = domain;
        this.storage = storage;
        this.state = MigrationState.NOT_STARTED;
    }

    // getters and setters

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public List<String> getMountPoints() { return this.mountPoints; }
    public void setMountPoints(List<String> mountPoints) { this.mountPoints = mountPoints; }

    // workload getters and setters

    public String getSourceIpAddress() { return sourceIpAddress; }
    public void setSourceIpAddress(String sourceIpAddress) { this.sourceIpAddress = sourceIpAddress; }

    public String getSourceUsername() { return sourceUsername; }
    public void setSourceUsername(String sourceUsername) { this.sourceUsername = sourceUsername; }

    public String getSourcePassword() { return sourcePassword; }
    public void setSourcePassword(String sourcePassword) { this.sourcePassword = sourcePassword; }

    public String getSourceDomain() { return sourceDomain; }
    public void setSourceDomain(String sourceDomain) { this.sourceDomain = sourceDomain; }

    public Map<String, Integer> getSourceStorage() { return sourceStorage; }
    public void setSourceStorage(Map<String, Integer> sourceStorage) { this.sourceStorage = sourceStorage; }

    // targetCloud getters and setters

    public CloudType getCloudType() { return cloudType; }
    public void setCloudType(CloudType cloudType) { this.cloudType = cloudType; }

    public String getCloudUsername() { return cloudUsername; }
    public void setCloudUsername(String cloudUsername) { this.cloudUsername = cloudUsername; }

    public String getCloudPassword() { return cloudPassword; }
    public void setCloudPassword(String cloudPassword) { this.cloudPassword = cloudPassword; }

    public String getCloudDomain() { return cloudDomain; }
    public void setCloudDomain(String cloudDomain) { this.cloudDomain = cloudDomain; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }

    public Map<String, Integer> getStorage() { return storage; }
    public void setStorage(Map<String, Integer> storage) { this.storage = storage; }

    public MigrationState getState() { return state; }
    public void setState(MigrationState state) { this.state = state; }
}
