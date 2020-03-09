package com.rivermeadow.java.springboot.demo.model;

import java.io.Serializable;

/**
 * Created by John Morning on 3/8/2020.
 */
public class Volume {

    private String mountPoint;
    private Integer totalSize;

    /**
     * Default Constructor
     */
    public Volume() { }

    /**
     * Constructor
     *
     * @param mountPoint - eg. c:\
     * @param totalSize - in kilobytes
     */
    public Volume(String mountPoint, Integer totalSize) {
        this.mountPoint = mountPoint;
        this.totalSize = totalSize;
    }

    // getters and setters

    public String getMountPoint() { return this.mountPoint; }
    public void setMountPoint(String mountPoint) { this. mountPoint =  mountPoint; }

    public Integer getTotalSize() { return this.totalSize; }
    public void setTotalSize(Integer totalSize) { this.totalSize = totalSize; }
}
