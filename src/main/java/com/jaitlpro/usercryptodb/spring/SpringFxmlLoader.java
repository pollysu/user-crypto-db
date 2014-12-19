package com.jaitlpro.usercryptodb.spring;

import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXMLLoader;

import org.springframework.context.ApplicationContext;

public class SpringFxmlLoader {

    private  ApplicationContext applicationContext;

    public SpringFxmlLoader(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Object load(String url) {
        try (InputStream fxmlStream = SpringFxmlLoader.class.getClassLoader().getResourceAsStream(url)) {

            FXMLLoader loader = new FXMLLoader();

            loader.setControllerFactory(clazz -> applicationContext.getBean(clazz));

            return loader.load(fxmlStream);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    public Object load(String url, Class<?> controllerClass) throws IOException
    {
        InputStream fxmlStream = null;
        try
        {
            fxmlStream = controllerClass.getClassLoader().getResourceAsStream(url);
            Object instance = applicationContext.getBean(controllerClass);
            FXMLLoader loader = new FXMLLoader();
            loader.getNamespace().put("controller", instance);
            return loader.load(fxmlStream);
        }
        finally
        {
            if (fxmlStream != null)
            {
                fxmlStream.close();
            }
        }
    }
}