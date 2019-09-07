package message.repositories;

import message.LDM.MessageModel;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageClusterGraphRepo extends Neo4jRepository<MessageModel,String> {

}
