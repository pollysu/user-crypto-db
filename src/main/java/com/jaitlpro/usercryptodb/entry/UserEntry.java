package com.jaitlpro.usercryptodb.entry;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class UserEntry {
    private String Id;
    private String login;
    private String fullName;
    private String address;
    private String cardNumber;
    private String CVVCode;

    public UserEntry(String login, String fullName, String address, String cardNumber, String CVVCode) {
        this.login = login;
        this.fullName = fullName;
        this.address = address;
        this.cardNumber = cardNumber;
        this.CVVCode = CVVCode;
    }

    public UserEntry() {
    }

    public String getId() {

        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCVVCode() {
        return CVVCode;
    }

    public void setCVVCode(String CVVCode) {
        this.CVVCode = CVVCode;
    }

    public String toXML(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(baos);
        xmlEncoder.writeObject(this);
        xmlEncoder.close();

        return baos.toString();
    }

    public static UserEntry getUserEntryFromXML(String xml) {
        XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(xml.getBytes()));
        UserEntry p = (UserEntry)decoder.readObject();
        decoder.close();
        return p;
    }

    @Override
    public String toString() {
        return "UserEntry{" +
                "Id='" + Id + '\'' +
                ", login='" + login + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", CVVCode='" + CVVCode + '\'' +
                '}';
    }
}
