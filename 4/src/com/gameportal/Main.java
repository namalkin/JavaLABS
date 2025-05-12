package com.gameportal;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        // Путь к веб-ресурсам
        String webappDirLocation = "web";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // Создаем контекст приложения
        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("Запуск приложения из: " + new File(webappDirLocation).getAbsolutePath());

        // Запускаем сервер
        tomcat.start();
        tomcat.getServer().await();
    }
}
