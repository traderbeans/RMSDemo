package com.rivermeadow.java.springboot.demo.repository;

import com.rivermeadow.java.springboot.demo.model.Workload;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by John Morning on 3/8/2020.
 */
public interface SourceRepository extends CrudRepository<Workload, UUID> {
}
