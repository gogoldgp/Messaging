package message.repositories;

import message.LDM.MeesageModel;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageClusterGraphRepo extends Neo4jRepository<MeesageModel,String> {

}
