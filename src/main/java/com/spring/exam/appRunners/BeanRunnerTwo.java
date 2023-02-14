package com.spring.exam.appRunners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(value=2)
@Slf4j
@Component
public class BeanRunnerTwo implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("Bean Two run method Started !!" );

    }

}