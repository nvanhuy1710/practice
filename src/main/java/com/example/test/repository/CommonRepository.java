package com.example.test.repository;

import java.util.Collection;
import java.util.Set;

public interface CommonRepository<T> {
    public T save(T domain);

    public Set<T> save(Collection<T> domains);

    public void delete(T domain);

    public T findById(String id);

    public Set<T> findAll();
}