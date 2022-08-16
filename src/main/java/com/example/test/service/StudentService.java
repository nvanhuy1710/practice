package com.example.test.service;

import com.example.test.domain.Student;
import com.example.test.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private static final List<Integer> idtmp = new ArrayList<>();

    public static Integer getId() {
        return idtmp.get(0);
    }

    public static boolean checkIdList() {
        return !idtmp.isEmpty();
    }

    public static void idUsed(Integer a) {
        idtmp.remove(a);
    }

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
        return name.trim();
    }

    @Autowired
    private StudentRepository repository;

    public List<Student> findAll() {
        return repository.findAll();
    }

    public List<Student> getMaxDTB() {
        List<Student> maxDTB = new ArrayList<>();
        List<Student> studentList = findAll();
        float max = 0;
        for(Student a : studentList) {
            if(a.getDTB() > max) {
                max = a.getDTB();
            }
        }
        for(Student a : studentList) {
            if(a.getDTB() == max) {
                maxDTB.add(a);
            }
        }
        return maxDTB;
    }

    public void add(Student student) {
        repository.save(student);
    }

    public void delete(Integer id) {
        idtmp.add(id);
        repository.delete(repository.findById(id).get());
    }

    public List<Student> findByName(String name) {
        return repository.findByName(name);
    }
    public Integer getAmount() {
        return (int) repository.count();
    }
}
