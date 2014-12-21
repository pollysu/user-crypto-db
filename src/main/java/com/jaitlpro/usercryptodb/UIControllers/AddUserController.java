package com.jaitlpro.usercryptodb.UIControllers;


import com.jaitlpro.usercryptodb.user.Users;
import com.jaitlpro.usercryptodb.entry.UserEntry;
import com.jaitlpro.usercryptodb.exception.FieldNotFilledException;
import com.jaitlpro.usercryptodb.exception.UserIsExistException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class AddUserController {

    static final Logger log = Logger.getLogger(AddUserController.class);

    @Autowired
    private Users users;

    private boolean isError;

    @FXML
    private TextField login;
    @FXML
    private TextField FIO;
    @FXML
    private TextField address;
    @FXML
    private TextField numberCard;
    @FXML
    private TextField CVVCode;
    @FXML
    private Label error;

    @FXML
    private void saveUser(ActionEvent event) {

        if(isError())
            hideError();

        UserEntry user = null;

        try {
            user = getUser();

            try {
                users.saveUser(user);
                clearFields();
            } catch (UserIsExistException e) {
                log.error("UserIsExistException", e);
                showError(e.getMessage());
            }

        } catch (FieldNotFilledException e) {
            log.error("FieldNotFilledException", e);
            showError(e.getMessage());
        }
    }

    private boolean isError() {
        return error.isVisible();
    }

    private void showError(String textError) {
        error.setVisible(true);
        error.setText(textError);
    }

    private void hideError() {
        error.setVisible(false);
    }

    private UserEntry getUser() throws FieldNotFilledException {
        UserEntry user = new UserEntry();

        if(login.getText().length() > 0) {
            user.setLogin(login.getText());
        } else {
            throw new FieldNotFilledException("Поле логин не заполнено!");
        }

        if(FIO.getText().length() > 0) {
            user.setFullName(FIO.getText());
        } else {
            throw new FieldNotFilledException("Поле ФИО не заполнено!");
        }

        if(address.getText().length() > 0) {
            user.setAddress(address.getText());
        } else {
            throw new FieldNotFilledException("Поле адрес не заполнено!");
        }

        if(numberCard.getText().length() > 0) {
            user.setCardNumber(numberCard.getText());
        } else {
            throw new FieldNotFilledException("Поле номер карты не заполнено!");
        }

        if(CVVCode.getText().length() > 0) {
            user.setCVVCode(CVVCode.getText());
        } else {
            throw new FieldNotFilledException("Поле CVV код карты не заполнено!");
        }

        return user;
    }

    private void clearFields() {
        login.setText("");
        FIO.setText("");
        address.setText("");
        numberCard.setText("");
        CVVCode.setText("");
    }
}
