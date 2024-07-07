package ru.sedov.task3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sedov.task3.config.SpringConfig;
import ru.sedov.task3.controller.UserController;

public class Main {
    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserController controller = context.getBean(UserController.class);
        System.out.println(controller.getUsers());
    }
}
