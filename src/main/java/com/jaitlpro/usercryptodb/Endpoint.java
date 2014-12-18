package com.jaitlpro.usercryptodb;

import com.jaitlpro.usercryptodb.crypto.CryptingUser;
import com.jaitlpro.usercryptodb.dao.UserDAO;
import com.jaitlpro.usercryptodb.entry.UserCryptEntry;
import com.jaitlpro.usercryptodb.entry.UserEntry;
import com.jaitlpro.usercryptodb.exception.UserIsExistException;
import com.jaitlpro.usercryptodb.exception.UserNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Endpoint {

    static final Logger log = Logger.getLogger(Endpoint.class);
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring/spring-context.xml");

        log.info("log work well!");

        UserDAO userDAO = context.getBean(UserDAO.class);

        /*UserEntry userJaitl = new UserEntry("Jaitl", "Игорь", "Москва", "1234567890","123" );
        UserEntry userJames = new UserEntry("James", "Женя", "Москва", "5634534534","543" );

        UserCryptEntry cryptoJaitl = CryptingUser.encryptUser(userJaitl);
        UserCryptEntry cryptoJames = CryptingUser.encryptUser(userJames);


        try {
            userDAO.saveUser(cryptoJaitl);
        } catch (UserIsExistException e) {
            e.printStackTrace();
        }

        try {
            userDAO.saveUser(cryptoJames);
        } catch (UserIsExistException e) {
            e.printStackTrace();
        }*/

        UserCryptEntry ctyptoJaitl = null;

        try {
            ctyptoJaitl = userDAO.findUserByLogin("Jaitl");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        UserEntry userEntry = CryptingUser.decryptUser(ctyptoJaitl);

        System.out.println(userEntry.toString());
    }
}
