package com.example.test.repository;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Repository;

import com.example.test.domain.Student;

@Repository
public class StudentRepository implements CommonRepository<Student> {
    private Map<String, Student> students = new HashMap<>();

    @Override
    public Student save(Student domain) {
        Student result = students.get(domain.getId());
        if (result != null) {
            result.setDTB(domain.getDTB());
            result.setName(domain.getName());
            result.setOld(domain.getOld());
            domain = result;
        }
        students.put(domain.getId(), domain);
        return students.get(domain.getId());
    }

    @Override
    public Set<Student> save(Collection<Student> domains) {
        for (Student student : domains) {
            save(student);
        }
        return findAll();
    }

    @Override
    public void delete(Student domain) {
        students.remove(domain.getId());
    }

    @Override
    public Student findById(String id) {
        return students.get(id);
    }

    @Override
    public Set<Student> findAll() {
        Set<Student> a = new TreeSet<>(new SortId());
        Set<String> b = students.keySet();
        for (String c : b) {
            a.add(students.get(c));
        }
        return a;
    }

    class SortId implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            // TODO Auto-generated method stub
            int a = Integer.valueOf(o1.getId());
            int b = Integer.valueOf(o2.getId());
            return (a > b) ? 1 : -1;
        }
    }
}


