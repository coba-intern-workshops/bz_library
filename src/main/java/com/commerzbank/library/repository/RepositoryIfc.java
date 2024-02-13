package com.commerzbank.library.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositoryIfc<T> {

    List<T> findAll();

    T save(T object);

    Optional<T> findById(UUID id);

    void delete(T object);

    void deleteById(UUID id);

    Long count();

}
