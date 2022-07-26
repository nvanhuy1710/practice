package com.example.test.domain;

public class StudentBuilder {
    private static StudentBuilder instance = new StudentBuilder();
    private String id = null;
    private String name = null;
    private Integer old = null;
    private Float DTB = null;

    private StudentBuilder() {

    }

    public static StudentBuilder create() {
        return instance;    
    }

    public StudentBuilder withId(String id) {
        this.id = id;
        return instance;
    }

    public StudentBuilder withName(String name) {
        this.name = name;
        return instance;
    }

    public StudentBuilder withOld(int old) {
        this.old = old;
        return instance;
    }

    public StudentBuilder withDTB(float DTB) {
        this.DTB = DTB;
        return instance;
    }

    public Student build() {
        Student result = new Student(this.name, this.old, this.DTB);
        if(id != null) {
            result.setId(id);
        }
        return result;
    }
}
