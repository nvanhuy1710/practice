package com.example.test.domain;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class Student {
    @NotNull
    private String id;
    @NotNull
    @NotBlank
    private String name;
    private int old;
    private float DTB;
    
    public Student() {
        this.id = UUID.randomUUID().toString();
    }

    public Student(String name, int old, float DTB) {
        this();
        this.name = name;
        this.old = old;
        this.DTB = DTB;
    }
}
