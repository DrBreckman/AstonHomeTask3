package ru.sedov.task3;

import org.codehaus.plexus.personality.plexus.lifecycle.phase.Suspendable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sedov.task3.config.SpringConfig;
import ru.sedov.task3.controller.BookController;
import ru.sedov.task3.controller.ReviewController;
import ru.sedov.task3.controller.UserController;
import ru.sedov.task3.dto.BookDto;
import ru.sedov.task3.dto.ReviewDto;
import ru.sedov.task3.entity.Book;
import ru.sedov.task3.entity.Review;
import ru.sedov.task3.entity.User;

public class Main {


    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserController userController = context.getBean(UserController.class);
        BookController bookController = context.getBean(BookController.class);
        ReviewController reviewController = context.getBean(ReviewController.class);

        System.out.println("*** USERS ***");

        for(var user : userController.getUsers())
            System.out.println(user.toString());

        System.out.println("*** BOOKS ***");

        for(var book : bookController.getAll())
            System.out.println(book.toString());

        System.out.println("*** REVIEWS ***");

        for(var review : reviewController.getAll())
            System.out.println(review);

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println(bookController.getByName("The Great Gatsby"));

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println(bookController.getByReaderName("Nikita"));
    }

}
