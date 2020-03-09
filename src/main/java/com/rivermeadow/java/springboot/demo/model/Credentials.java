package com.rivermeadow.java.springboot.demo.model;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.cassandra.core.mapping.Element;
//import org.springframework.data.cassandra.core.mapping.Tuple;

import javax.validation.constraints.NotNull;

/**
 * Created by John Morning on 3/8/2020
 */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Tuple
public class Credentials {

    @NotNull
//    @Element(0)
    private String username;
    @NotNull
//    @Element(1)
    private String password;
//    @Element(2)
    private String domain;
}
