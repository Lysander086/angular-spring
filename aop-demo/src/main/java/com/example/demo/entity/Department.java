package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
//    @OneToMany(mappedBy = "department")
//    // Many 是此注解所注解的属性，即User 。
//    private Set<User> users = new HashSet<User>();



}
