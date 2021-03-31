package com.example.jpademo.repo;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface PagingAndSortingRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {
    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<ID> ids);
}
