package com.example.demo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/*  JPA以Entity 为中心， 实体类就必须使用@Entity 来注解，*/
@Entity
@Getter
@Setter
public class User {

//    @ManyToOne
    // JoinColumn ，与ManyToOne 搭配使用，说明外键字段是department_id 。
//    @JoinColumn(name = "department_id")
//    Department department;

    @Id
    /*主键生成策略由注解@GeneratedValue 来指定*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //部门
    @Column
    private String name;
    //创建时间
    @Column(name = "create_time")
    private Date createTime;


    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
