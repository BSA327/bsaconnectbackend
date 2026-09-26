package com.company.bsaadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.CrudRepository;

public abstract class GenericService<T> {

    private final CrudRepository<T, Long> repository;

    public GenericService(CrudRepository<T, Long> repository) {
        this.repository = repository;
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public T findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
        new RuntimeException("Record not found: " + id));
    }
    
    public List<T> findAll() {
        List<T> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }

    public T update(Long id, T input) {

        T existing = findById(id);
                

        BeanUtils.copyProperties(
                input,
                existing,
                "id",
                "active",
                "createdDate",
                "createdBy",
                "updatedDate",
                "updatedBy"
        );

        return save(existing);
    }
}