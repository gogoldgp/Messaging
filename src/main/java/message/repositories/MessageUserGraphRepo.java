package message.repositories;

import message.LDM.MessageUser;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageUserGraphRepo extends Neo4jRepository<MessageUser,Long> {

    MessageUser findByEmailID(String emailID);

}