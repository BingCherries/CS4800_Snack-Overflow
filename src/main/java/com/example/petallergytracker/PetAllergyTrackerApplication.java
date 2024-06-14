package com.example.petallergytracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;

@SpringBootApplication // This annotation denotes a configuration class that declares one or more @Bean methods and
// also triggers autoconfiguration and component scanning.
@EnableAsync  // Enables Spring's asynchronous method execution capability.

public class PetAllergyTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetAllergyTrackerApplication.class, args); // Boots up the Spring application.
    }

    @Bean(name = "taskExecutor") // Defines a bean to be managed by the Spring container.
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2); // Sets the core number of threads.
        executor.setMaxPoolSize(2); // Sets the maximum allowed number of threads.
        executor.setQueueCapacity(500); // Sets the queue capacity for holding tasks before they are executed.
        executor.setThreadNamePrefix("PetAllergyLookup-"); // Sets the prefix for names of threads created by this executor.
        executor.initialize(); // Initializes the executor.
        return executor; // Returns the fully configured thread pool executor.
    }

}
