package com.example.counter.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CounterConfig {
    //////////////////////////会员号//////////////////////////
    @Value("${counter.id}")
    private short id;


    @Value("${counter.dataCenterId}")
    private long dataCenterId;

    @Value("${counter.workerId}")
    private long workerId;


}
