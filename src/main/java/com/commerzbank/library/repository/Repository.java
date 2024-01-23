package com.commerzbank.library.repository;

import java.util.List;

public interface Repository<T> {

    List<T> findAll();

    T save(T object);

}
