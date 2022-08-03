package com.example.test.domain;

public class StudentBuilder {
    private static final StudentBuilder instance = new StudentBuilder();
    private Integer id = null;
    private String name = null;
    private Integer old = null;
    private Float DTB = null;

    private StudentBuilder() {

    }

    public static StudentBuilder create() {
        return instance;    
    }

    public StudentBuilder withId(Integer id) {
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
        // if(id != null) {
        //     result.setId(id);
        // }
        return new Student(this.id, this.name, this.old, this.DTB);
    }
}
