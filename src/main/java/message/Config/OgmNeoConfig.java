package message.Config;

import org.apache.commons.lang3.StringUtils;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories("message.jpa")
public class OgmNeoConfig {
    String graphenedbURL = System.getenv("GRAPHENEDB_BOLT_URL");
    String graphenedbUser = System.getenv("GRAPHENEDB_BOLT_USER");
    String graphenedbPass = System.getenv("GRAPHENEDB_BOLT_PASSWORD");

    @Bean
    public Configuration getConfig(){

        if(StringUtils.isEmpty(graphenedbURL)){
            graphenedbURL = "bolt://localhost:7687";
            graphenedbUser = "neo4j";
            graphenedbPass = "neo4j";
        }
        Configuration configuration = new Configuration.Builder().uri(graphenedbURL).credentials(graphenedbUser,graphenedbPass).build();
        return configuration;
    }
    @Bean
    public SessionFactory getSessionFactory(){
        return new SessionFactory(getConfig(),"message.jpa");
    }
    @Bean(name = "neo4jTransactionManager")
    public PlatformTransactionManager transactionManager(){
        return new Neo4jTransactionManager(getSessionFactory());
    }

}
