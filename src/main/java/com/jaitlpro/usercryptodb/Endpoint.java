package com.jaitlpro.usercryptodb;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Endpoint {

    static final Logger log = Logger.getLogger(Endpoint.class);
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring/spring-context.xml");

        log.info("log work well!");
    }
}
