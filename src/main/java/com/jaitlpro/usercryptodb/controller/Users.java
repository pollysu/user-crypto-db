package com.jaitlpro.usercryptodb.controller;

import com.jaitlpro.usercryptodb.crypt.CryptingUser;
import com.jaitlpro.usercryptodb.dao.UserDAO;
import com.jaitlpro.usercryptodb.entry.UserCryptEntry;
import com.jaitlpro.usercryptodb.entry.UserEntry;
import com.jaitlpro.usercryptodb.exception.UserIsExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Users {

    @Autowired
    private UserDAO userDAO;

    public  void saveUser(UserEntry userEntry) throws UserIsExistException {
        UserCryptEntry  cryptEntry = CryptingUser.encryptUser(userEntry);

        userDAO.saveUser(cryptEntry);
    }
}
