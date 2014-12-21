package com.jaitlpro.usercryptodb.UIControllers;

import com.jaitlpro.usercryptodb.entry.UserEntry;
import com.jaitlpro.usercryptodb.exception.UserNotFoundException;
import com.jaitlpro.usercryptodb.user.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class FindUserByLoginController {

    static final Logger log = Logger.getLogger(FindUserByLoginController.class);

    @Autowired
    private Users users;

    @FXML
    private VBox userDataBox;
    @FXML
    private Label error;
    @FXML
    private TextField login;

    @FXML
    private Label loginLabel;
    @FXML
    private Label FIO;
    @FXML
    private Label address;
    @FXML
    private Label numberCard;
    @FXML
    private Label CVVCode;

    @FXML
    private void findUser(ActionEvent event) {
        if(userDataBoxIsVisible())
            hideUserDataBox();

        if(isError())
            hideError();

        if(login.getText().length() > 0) {

            try {
                UserEntry userEntry = users.findUserByLogin(login.getText());
                displayUserData(userEntry);
            } catch (UserNotFoundException e) {
                showError(e.getMessage());
                log.error("UserNotFoundException", e);
            }

        }
    }

    private boolean userDataBoxIsVisible() {
        return userDataBox.isVisible();
    }

    private void displayUserData(UserEntry userEntry) {
        loginLabel.setText(userEntry.getLogin());
        FIO.setText(userEntry.getFullName());
        address.setText(userEntry.getAddress());
        numberCard.setText(userEntry.getCardNumber());
        CVVCode.setText(userEntry.getCVVCode());

        showUserDataBox();
    }

    private void showUserDataBox() {
        userDataBox.setVisible(true);
    }

    private void hideUserDataBox() {
        userDataBox.setVisible(false);
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
}
