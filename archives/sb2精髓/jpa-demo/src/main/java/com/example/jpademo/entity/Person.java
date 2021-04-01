package com.example.jpademo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "age", length = 4)
    private int age;
}