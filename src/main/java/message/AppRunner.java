package message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
@EnableNeo4jRepositories("message.jpa")
@SpringBootApplication
@ComponentScan("message")
public class AppRunner {
    public static void main(String[] args) {
        SpringApplication.run(AppRunner.class, args);
    }
}

//    @Bean
//    CommandLineRunner commandLineRunner(MessageUserGraphRepo messageUserGraphRepo){
//
//        return args -> {
//            Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j"));
//                messageUserGraphRepo.deleteAll();
//                MessageUser gogol = new MessageUser();
//                MessageUser buban = new MessageUser();
//                MessageUser guddu = new MessageUser();
//                gogol.setEmailID("arkajit24@gmail.com");
//                buban.setEmailID("soumavanag@gmail.com");
//                guddu.setEmailID("souravchowdhury92@gmail.com");
//
//                gogol.setFriends(Arrays.asList(buban, guddu));
//                gogol.setUsername("gogol");
//                buban.setUsername("buban");
//                guddu.setUsername("guddu");
//
//                messageUserGraphRepo.save(gogol);
//                messageUserGraphRepo.save(buban);
//                messageUserGraphRepo.save(guddu);
//
//
//        };

//
//    }
//}
