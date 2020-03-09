package com.rivermeadow.java.springboot.demo.model;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.JsonProperty;
//import com.rivermeadow.java.springboot.demo.model.Credentials;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

/**
 * Created by John Morning on 3/8/2020.
 */
@Table("targetCloud")
public class TargetCloud implements Serializable {

    @PrimaryKey
    @CassandraType(type = DataType.Name. UUID)
    private UUID id;
    private CloudType cloudType;
    // Cloud Credentials
    private String cloudUsername;
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

    /**
     * Default Constructor - set UUID
     */
    public TargetCloud() {
        this.id = UUID.randomUUID();
    }

    /**
     * Constructor - All Args
     *
     * @param cloudType - four possible CloudTypes: AWS, AZURE, VSPHERE, VCLOUD
     * Cloud Credentials
     * @param cloudUsername - cloud credentials
     * @param cloudPassword - cloud credentials
     * @param cloudDomain - cloud credentials
     * Target Workload
     * @param ipAddress - IP Address
     * @param username - workload credentials
     * @param password - workload credentials
     * @param domain - workload credentials
     * @param storage - map of volumes ( mountPoint, totalSize )
     */
    public TargetCloud(@JsonProperty("cloudType") CloudType cloudType,
                       @JsonProperty("cloudUsername") String cloudUsername,
                       @JsonProperty("cloudPassword") String cloudPassword,
                       @JsonProperty("cloudDomain") String cloudDomain,
                       @JsonProperty("ipAddress") String ipAddress,
                       @JsonProperty("username") String username,
                       @JsonProperty("password") String password,
                       @JsonProperty("domain") String domain,
                       @JsonProperty("storage") Map<String, Integer> storage) {
        this();
        this.cloudType = cloudType;
        this.cloudUsername = cloudUsername;
        this.cloudPassword = cloudPassword;
        this.cloudDomain = cloudDomain;
        this.ipAddress = ipAddress;
        this.username = username;
        this.password = password;
        this.domain = domain;
        this.storage = storage;
    }

    // getters and setters

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public CloudType getCloudType() { return this.cloudType; }
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
}
