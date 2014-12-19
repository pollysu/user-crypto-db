package com.jaitlpro.usercryptodb.user;

import com.jaitlpro.usercryptodb.crypt.CryptingUser;
import com.jaitlpro.usercryptodb.dao.UserDAO;
import com.jaitlpro.usercryptodb.entry.UserCryptEntry;
import com.jaitlpro.usercryptodb.entry.UserEntry;
import com.jaitlpro.usercryptodb.exception.UserIsExistException;
import com.jaitlpro.usercryptodb.exception.UserNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Users {

    static final Logger log = Logger.getLogger(Users.class);

    @Autowired
    private UserDAO userDAO;

    public void saveUser(UserEntry userEntry) throws UserIsExistException {
        log.info(String.format("Save user: %s", userEntry.getLogin()));

        UserCryptEntry  cryptEntry = CryptingUser.encryptUser(userEntry);

        userDAO.saveUser(cryptEntry);
    }

    public UserEntry findUserByLogin(String login) throws UserNotFoundException {
        log.info(String.format("Find user by login: %s", login));

        UserCryptEntry userCryptEntry = userDAO.findUserByLogin(login);

        UserEntry userEntry = CryptingUser.decryptUser(userCryptEntry);

        return userEntry;
    }
}
