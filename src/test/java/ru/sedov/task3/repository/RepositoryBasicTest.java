package ru.sedov.task3.repository;

import junit.framework.TestCase;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sedov.task3.MyPostgresContainer;
import ru.sedov.task3.config.DatabaseConfig;
import ru.sedov.task3.config.DispatcherServletInitializer;
import ru.sedov.task3.config.SpringConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
    SpringConfig.class,
    DispatcherServletInitializer.class,
    DatabaseConfig.class
})
public abstract class RepositoryBasicTest<R>  extends TestCase {

    private final R repository;

    @ClassRule
    public static MyPostgresContainer container = MyPostgresContainer.getInstance();

    public RepositoryBasicTest(Class<? extends R>  repositoryClass) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        repository = context.getBean(repositoryClass);

        initDb();
    }

    @BeforeAll
    static void beforeAll() {
        container.start();
    }

    @AfterAll
    static void afterAll() {
        container.stop();
    }

    public R getTestingRepository() {

        return repository;
    }

    public abstract void initDb();
}
