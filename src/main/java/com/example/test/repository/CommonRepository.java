package com.example.test.repository;

import java.util.Collection;
import java.util.Set;

public interface CommonRepository<T> {
    public T save(T domain);

    public Set<T> save(Collection<T> domains);

    public void delete(T domain);

    public T findById(Integer id);

    public Set<T> findAll();

    public Integer getLast();

    public Set<T> maxDTB();

    public Set<T> searchByDTB(Float dtb);
}