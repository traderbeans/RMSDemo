package com.rivermeadow.java.springboot.demo.model;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

/**
 * Created by John Morning on 3/8/2020.
 */
@Table("workload")
public class Workload implements Serializable {

    @PrimaryKey
    @CassandraType(type = DataType.Name. UUID)
    private UUID id;
    @NotNull
    private String ipAddress;
    // Credentials
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String domain;
    // Volume( mountPoint, totalSize )
    private Map<String, Integer> storage;

    /**
     * Default Constructor - set UUID
     */
    public Workload() {
        this.id = UUID.randomUUID();
    }

    /**
     * Constructor
     *
     * @param ipAddress - IP Address
     * @param username - credentials
     * @param password - credentials
     * @param domain - credentials
     * @param storage - map of volumes ( mountPoint, totalSize )
     */
    public Workload(@JsonProperty("ipAddress") String ipAddress,
                    @JsonProperty("username") String username,
                    @JsonProperty("password") String password,
                    @JsonProperty("domain") String domain,
                    @JsonProperty("storage") Map<String, Integer> storage) {
        this();
        this.ipAddress = ipAddress;
        this.username = username;
        this.password = password;
        this.domain = domain;
        this.storage = storage;
    }

    // getters and setters

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getIpAddress() { return this.ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getUsername() { return this.username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }

    public String getDomain() { return this.domain; }
    public void setDomain(String domain) { this.domain = domain; }

    public Map<String, Integer> getStorage() { return this.storage; }
    public void setStorage(Map<String, Integer> storage) { this.storage = storage; }
}
