package com.university.contractors;

import com.university.contractors.repository.core.CoreRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Entry point to Application.
 *
 * @author Oleg Barmin
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.university.contractors.repository"}, repositoryBaseClass = CoreRepositoryImpl.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
