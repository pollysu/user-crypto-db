package com.jaitlpro.usercryptodb.UIControllers;

import com.jaitlpro.usercryptodb.spring.SpringFxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MainMenuController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @FXML
    private void showFindUserByLogin(ActionEvent event) {
        Stage stage = new Stage();

        SpringFxmlLoader loader = new SpringFxmlLoader(applicationContext);

        Parent root = (Parent) loader.load("view/findUserByLogin.fxml");

        stage.setTitle("Добавление пользователя");
        Scene scene = new Scene(root, 500, 400);

        scene.getStylesheets().add("view/style.css");

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addUser(ActionEvent event) {
        Stage stage = new Stage();

        SpringFxmlLoader loader = new SpringFxmlLoader(applicationContext);

        Parent root = (Parent) loader.load("view/addUser.fxml");

        stage.setTitle("Добавление пользователя");
        Scene scene = new Scene(root, 400, 400);

        scene.getStylesheets().add("view/style.css");

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
