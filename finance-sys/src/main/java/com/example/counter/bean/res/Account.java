package com.example.counter.bean.res;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @NotNull
    private int id;
    @NotNull
    private long uid;
    @NotNull
    private String lastLoginDate;
    @NotNull
    private String lastLoginTime;
    private String token;
}
