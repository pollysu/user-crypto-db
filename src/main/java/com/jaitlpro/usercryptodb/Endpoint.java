package com.jaitlpro.usercryptodb;

import com.jaitlpro.usercryptodb.crypto.EnocryptUser;
import com.jaitlpro.usercryptodb.entry.UserEntry;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Endpoint {

    static final Logger log = Logger.getLogger(Endpoint.class);
    public static void main(String[] args) {
        /*ApplicationContext context =
                new ClassPathXmlApplicationContext("spring/spring-context.xml");

        log.info("log work well!");

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        UserEntry user = new UserEntry("jaitl", "Игорь", "Москва", "1234567890","123" );

        EnocryptUser crypto = new EnocryptUser();

        crypto.decrytpoUser(crypto.enocrypt(user));

    }
}
