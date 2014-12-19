package com.jaitlpro.usercryptodb;

import com.jaitlpro.usercryptodb.spring.SpringFxmlLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main extends Application {

   static final Logger log = Logger.getLogger(Main.class);

    private final ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("spring/spring-context.xml");

    private final SpringFxmlLoader loader = new SpringFxmlLoader(applicationContext);

    @Override
    public void start(Stage primaryStage) throws Exception{
        log.info("Load menu window");

        Parent root = (Parent) loader.load("view/mainMenu.fxml");

        primaryStage.setTitle("Добавление пользователя");
        Scene scene = new Scene(root, 300, 200);

        scene.getStylesheets().add("view/style.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
