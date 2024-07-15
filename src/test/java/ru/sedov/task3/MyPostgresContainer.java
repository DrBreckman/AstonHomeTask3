package ru.sedov.task3;

import org.testcontainers.containers.PostgreSQLContainer;

public class MyPostgresContainer extends PostgreSQLContainer {

    private static final String IMAGE_VERSION = "postgres:13";
    private static MyPostgresContainer container;

    private MyPostgresContainer() {
        super(IMAGE_VERSION);
    }

    public static MyPostgresContainer getInstance() {
        if (container == null) {
            container = new MyPostgresContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }

}
