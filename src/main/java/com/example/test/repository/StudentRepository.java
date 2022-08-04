package com.example.test.repository;

import com.example.test.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository implements CommonRepository<Student> {
    private Map<Integer, Student> students = new HashMap<>();

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
    public Student findById(Integer id) {
        return students.get(id);
    }

    @Override
    public  Set<Student> findByName(String name) {
        Set<Integer> b = students.keySet();
        Set<Student> a = new TreeSet<>(new SortId());
        for (Integer c : b) {
            Student tmp = students.get(c);
            if(name.equals(tmp.getName())) {
                a.add(tmp);
            }
        }
        return a;
    }

    @Override
    public Set<Student> findAll() {
        Set<Student> a = new TreeSet<>(new SortId());
        Set<Integer> b = students.keySet();
        for (Integer c : b) {
            a.add(students.get(c));
        }
        return a;
    }

    class SortId implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            int a = Integer.valueOf(o1.getId());
            int b = Integer.valueOf(o2.getId());
            return (a > b) ? 1 : -1;
        }
    }

    @Override
    public Integer getLast() {
        Integer d = 0;
        Set<Integer> b = students.keySet();
        for (Integer c : b) {
            Student tmp = students.get(c);
            if(d < tmp.getId()) {
                d = tmp.getId();
            }
        }
        return d;
    }

    @Override
    public Set<Student> maxDTB() {
        float max = 0;
        Set<Student> set = findAll();
        for(Student tmp : set) {
            if(max < tmp.getDTB()) {
                max = tmp.getDTB();
            }
        }
        Set<Student> maxDTBs = new TreeSet<>(new SortId());
        for(Student tmp : set) {
            if(tmp.getDTB() >= max) {
                maxDTBs.add(tmp);
            }
        }
        return maxDTBs;
    }
}


