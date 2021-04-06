package com.example.demo.dao;

import com.example.demo.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkerDao extends JpaRepository<Worker, Long> {

    List<Worker> findByName(String name);
}
