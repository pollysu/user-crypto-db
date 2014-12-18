package com.jaitlpro.usercryptodb.entry;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class UserCryptoEntry {

    @Id
    private String id;
    private String login;
    private byte[] cryptoKey;
    private byte[] cryptoData;

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

    public byte[] getCryptoKey() {
        return cryptoKey;
    }

    public void setCryptoKey(byte[] cryptoKey) {
        this.cryptoKey = cryptoKey;
    }

    public byte[] getCryptoData() {
        return cryptoData;
    }

    public void setCryptoData(byte[] cryptoData) {
        this.cryptoData = cryptoData;
    }
}
