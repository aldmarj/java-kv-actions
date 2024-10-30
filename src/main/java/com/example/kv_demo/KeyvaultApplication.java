package com.example.kv_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

@SpringBootApplication
public class KeyvaultApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeyvaultApplication.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> basicsApplicationListener(TodoRepository repository) {
        return event->repository
            .saveAll(Stream.of("A", "B", "C").map(name->new Todo("configuration", "congratulations, you have set up " + "correctly!", true)).toList())
            .forEach(System.out::println);
    }

}

interface TodoRepository extends JpaRepository<Todo, Long> {

}


// import com.azure.security.keyvault.secrets.SecretClient;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// public class KvDemoApplication implements CommandLineRunner {

//     // Spring Cloud Azure will automatically inject SecretClient in your ApplicationContext.
//     private final SecretClient secretClient;

//     public KvDemoApplication(SecretClient secretClient) {
//         this.secretClient = secretClient;
//     }

//     public static void main(String[] args) {
//         SpringApplication.run(KvDemoApplication.class, args);
//     }

//     @Override
//     public void run(String... args) {
//         System.out.println("h2url: " + secretClient.getSecret("h2url").getValue());
//     }
// }


