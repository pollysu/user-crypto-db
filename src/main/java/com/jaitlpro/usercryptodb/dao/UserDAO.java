package com.jaitlpro.usercryptodb.dao;

import com.jaitlpro.usercryptodb.entry.UserCryptEntry;
import com.jaitlpro.usercryptodb.exception.UserIsExistException;
import com.jaitlpro.usercryptodb.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveUser(UserCryptEntry user) throws UserIsExistException {

        Query query = new Query(Criteria.where("login").is(user.getLogin()));

        UserCryptEntry findUser = mongoTemplate.findOne(query, UserCryptEntry.class);

        if(findUser != null)
            throw new UserIsExistException("Пользователь с таким логином уже существует в базе данных");

        mongoTemplate.insert(user);
    }

    public UserCryptEntry findUserByLogin(String login) throws UserNotFoundException {
        Query query = new Query(Criteria.where("login").is(login));

        UserCryptEntry findUser = mongoTemplate.findOne(query, UserCryptEntry.class);

        if(findUser == null)
            throw new UserNotFoundException("Пользователь с таким логином не найден в базе данных");

        return findUser;
    }
}
