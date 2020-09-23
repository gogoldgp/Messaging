package message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@EnableNeo4jRepositories("message.jpa")
@SpringBootApplication
public class AppRunner {
    public static void main(String[] args) {
        SpringApplication.run(AppRunner.class, args);

    }
}



