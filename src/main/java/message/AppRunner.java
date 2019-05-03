package message;

import message.LDM.MessageUser;
import message.repositories.MessageUserGraphRepo;
import org.neo4j.driver.v1.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Arrays;


import static org.neo4j.driver.v1.Config.build;
import static org.neo4j.driver.v1.Values.parameters;


@EnableNeo4jRepositories
@SpringBootApplication
public class AppRunner {
public static void main(String [] args) {
    SpringApplication.run(AppRunner.class,args);
}

    @Bean
    CommandLineRunner commandLineRunner(MessageUserGraphRepo messageUserGraphRepo){

        return args -> {
            Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j"));
                messageUserGraphRepo.deleteAll();
                MessageUser gogol = new MessageUser();
                MessageUser buban = new MessageUser();
                MessageUser guddu = new MessageUser();
                gogol.setEmailID("arkajit24@gmail.com");
                buban.setEmailID("soumavanag@gmail.com");
                guddu.setEmailID("souravchowdhury92@gmail.com");

                gogol.setFriends(Arrays.asList(buban, guddu));
                gogol.setUsername("gogol");
                buban.setUsername("buban");
                guddu.setUsername("guddu");

                messageUserGraphRepo.save(gogol);
                messageUserGraphRepo.save(buban);
                messageUserGraphRepo.save(guddu);


        };


    }
}
