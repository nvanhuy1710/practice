package com.example.test.repository;

import java.util.Collection;
import java.util.Set;

public interface CommonRepository<T> {
    public T save(T domain);

    public Set<T> save(Collection<T> domains);

    public void delete(T domain);

    public T findById(Integer id);

    public Set<T> findByName(String name);

    public Set<T> findAll();

    public Integer getLast();

    public Set<T> maxDTB();

    public static String nameFormat(String name) {
        char[] charArray = name.toCharArray();
        for(int i = 0; i < charArray.length; i++) {
            if(i == 0 || charArray[i-1] == 32) {
                charArray[i] = Character.toUpperCase(charArray[i]);
            }
            else {
                charArray[i] = Character.toLowerCase(charArray[i]);
            }
        }
        name = String.valueOf(charArray);
        return name;
    }
}