package com.jaitlpro.usercryptodb.dao;

import com.jaitlpro.usercryptodb.entry.UserCryptEntry;
import com.jaitlpro.usercryptodb.exception.UserIsExistException;
import com.jaitlpro.usercryptodb.exception.UserNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    static final Logger log = Logger.getLogger(UserDAO.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveUser(UserCryptEntry user) throws UserIsExistException {

        log.info(String.format("Save user: %s", user.getLogin()));

        Query query = new Query(Criteria.where("login").is(user.getLogin()));

        UserCryptEntry findUser = mongoTemplate.findOne(query, UserCryptEntry.class);

        if(findUser != null){
            log.error("throw new UserIsExistException()");
            throw new UserIsExistException("Пользователь с таким логином уже существует в базе данных");
        }

        log.info("Write user to DB");
        mongoTemplate.insert(user);
    }

    public UserCryptEntry findUserByLogin(String login) throws UserNotFoundException {

        log.info(String.format("Find user by login: %s", login));

        Query query = new Query(Criteria.where("login").is(login));

        UserCryptEntry findUser = mongoTemplate.findOne(query, UserCryptEntry.class);

        if(findUser == null) {
            log.error("throw new UserNotFoundException()");
            throw new UserNotFoundException("Пользователь с таким логином не найден в базе данных");
        }

        return findUser;
    }
}
