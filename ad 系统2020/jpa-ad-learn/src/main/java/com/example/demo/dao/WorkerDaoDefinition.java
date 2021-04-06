package com.example.demo.dao;

import com.example.demo.entity.Worker;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

/* 不常用 , 一般直接集成 jpaRepository */
@RepositoryDefinition(domainClass = Worker.class, idClass = Long.class)
public interface WorkerDaoDefinition {
    List<Worker> findByName(String name);

}
