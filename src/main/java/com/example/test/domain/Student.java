package com.example.test.domain;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Student {
    @NotNull
    private Integer id;
    @NotNull
    @NotBlank
    private String name;
    private int old;
    private float DTB;
    
    public Student() {
        this.id = 0;
    }

    public Student(Integer id, String name, int old, float DTB) {
        //this();
        this.id = id;
        this.name = name;
        this.old = old;
        this.DTB = DTB;
    }
}
