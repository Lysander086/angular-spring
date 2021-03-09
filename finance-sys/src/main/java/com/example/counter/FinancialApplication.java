package com.example.counter;

import com.example.counter.config.CounterConfig;
import com.example.counter.thirdParty.uuid.GudyUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class FinancialApplication {

    @Autowired
    private CounterConfig counterConfig;

    @PostConstruct
    private void init(){
        GudyUuid.getInstance().init(counterConfig.getDataCenterId(),counterConfig.getWorkerId());
    }

    public static void main(String[] args) {
        SpringApplication.run(FinancialApplication.class, args);
    }


}
