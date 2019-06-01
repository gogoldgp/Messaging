package message.repositories;

import message.LDM.MessageUser;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageUserGraphRepo extends Neo4jRepository<MessageUser,Long> {

    @Query("match (U:MessageUser) where U.emailID = {emailID} return U")
    MessageUser findByEmailID(@Param("emailID") String emailID);

}