package com.jaitlpro.usercryptodb.entry;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class UserCryptEntry {

    @Id
    private String id;
    private String login;
    private byte[] cryptKey;
    private byte[] cryptData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getCryptKey() {
        return cryptKey;
    }

    public void setCryptKey(byte[] cryptKey) {
        this.cryptKey = cryptKey;
    }

    public byte[] getCryptData() {
        return cryptData;
    }

    public void setCryptData(byte[] cryptData) {
        this.cryptData = cryptData;
    }
}
