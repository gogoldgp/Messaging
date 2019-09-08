package message.Config;

import org.apache.commons.lang3.StringUtils;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories("message.jpa")
public class OgmNeoConfig {
    @Value("${spring.data.neo4j.uri:null}")
    private String graphenedbURL;
    @Value("${spring.data.neo4j.username:null}")
    private String graphenedbUser;
    @Value("${spring.data.neo4j.password:null}")
    private String graphenedbPass;

    @Bean
    public Configuration getConfig(){

        if(StringUtils.isEmpty(graphenedbURL) && StringUtils.isEmpty(graphenedbUser)
                && StringUtils.isEmpty(graphenedbPass)  ){
            graphenedbURL = System.getenv("GRAPHENEDB_BOLT_URL");
            graphenedbUser = System.getenv("GRAPHENEDB_BOLT_USER");
            graphenedbPass = System.getenv("GRAPHENEDB_BOLT_PASSWORD");
        }
        Configuration configuration = new Configuration.Builder().uri(graphenedbURL).credentials(graphenedbUser,graphenedbPass).build();
        return configuration;
    }
    @Bean
    public SessionFactory getSessionFactory(){
        return new SessionFactory(getConfig(),"message.jpa");
    }
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(){
        return new Neo4jTransactionManager(getSessionFactory());
    }

}
